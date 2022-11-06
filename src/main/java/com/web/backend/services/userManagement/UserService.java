package com.web.backend.services.userManagement;

import com.web.backend.dto.userManagement.ExtendedUserData;
import com.web.backend.dto.userManagement.UserSearchSortParameters;
import com.web.backend.dto.userManagement.UserSimple;
import com.web.backend.exception.NotFoundException;
import com.web.backend.model.job.Job;
import com.web.backend.model.user.AppUser;
import com.web.backend.repositories.userManagement.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service @AllArgsConstructor @Slf4j
public class UserService {
    private final UserRepository repo;
    private final MongoTemplate template;

    public ExtendedUserData getUser(String email) {
        log.info("Getting details of user with email : {}", email);
        AppUser user = repo.findByEmail(email).orElseThrow(NotFoundException::new);

        log.info("Getting job details of user with email : {}", email);
        var aggregationPipeline = new ArrayList<AggregationOperation>();
        aggregationPipeline.add(Aggregation.match(Criteria.where("client.email").regex(email)));
        var aggregation = Aggregation.newAggregation(aggregationPipeline);
        List<Job> jobList= template.aggregate(aggregation, "job", Job.class).getMappedResults();

        log.info("Got {} jobs. Analyzing data and returning...", jobList.size());
        return new ExtendedUserData(user, jobList);
    }

    public void changeUserRole(String email, String role) {
        log.info("Getting details of user with email : {}", email);
        AppUser dbUser = repo.findByEmail(email).orElseThrow(NotFoundException::new);

        log.info("Changing user role...");
        dbUser.setPermissions(List.of(role.toUpperCase(Locale.ROOT)));

        log.info("Saving edited user...");
        repo.save(dbUser);
    }

    public Page<UserSimple> getUserList(UserSearchSortParameters searchParams) {
        //Making aggregation pipeline.
        log.info("Building aggregation pipeline...");
        var pageRequest = PageRequest.of(searchParams.getPgNum(), searchParams.getPgSize());
        var aggregationPipeline = new ArrayList<AggregationOperation>();

        //$concat for name, $first for permissions
        //TODO: Aggregation to turn user into user simple
        aggregationPipeline.add(Aggregation.project()
                .andExpression("{$toString : {$getField : {$literal: '_id'}}}").as("tempId") //NECESSARY TO DO ID SEARCHING. WE DO NOT KEEP THIS FIELD IN JOB SIMPLE CLASS THOUGH
                .andExpression("concat(firstName,' ',lastName)").as("name")
                .andExpression("first(permissions)").as("type")
                .andExpression("{$getField : {$literal: 'email'}}").as("email")
        );

        log.info("Modifying pipeline according to searching parameters...");
        switch(searchParams.getShowSelect()) {
            case "client" -> aggregationPipeline.add(Aggregation.match(Criteria.where("type").is("USER")));
            case "admin" -> aggregationPipeline.add(Aggregation.match(Criteria.where("type").is("ADMIN")));
        }

        switch(searchParams.getSearchSelect()) {
            case "email" -> aggregationPipeline.add(Aggregation.match(Criteria.where("email").regex(searchParams.getSearchString(), "i")));
            case "name" -> aggregationPipeline.add(Aggregation.match(Criteria.where("name").regex(searchParams.getSearchString(), "i")));
            case "tempId" -> aggregationPipeline.add(Aggregation.match(Criteria.where("_id").is(new ObjectId(searchParams.getSearchString()))));
        }

        log.info("Modifying pipeline according to sorting parameters...");
        if(!searchParams.getSortCol().isBlank()) {
            switch (searchParams.getSortDir()) {
                case "asc" -> aggregationPipeline.add(Aggregation.sort(Sort.Direction.ASC, searchParams.getSortCol()));
                case "desc" -> aggregationPipeline.add(Aggregation.sort(Sort.Direction.DESC, searchParams.getSortCol()));
            }
        }

        log.info("Limiting search according to page parameters...");
        aggregationPipeline.add(Aggregation.skip((pageRequest.getPageNumber() - 1) * pageRequest.getPageSize())); //TODO: Enable this once infinite scrolling is implemented.
        aggregationPipeline.add(Aggregation.limit(pageRequest.getPageSize()));

        log.info("Finalizing aggregation pipeline...");
        var aggregation = Aggregation.newAggregation(aggregationPipeline);

        log.info("Creating the job simple page. Length of aggregation pipeline is {}...", aggregationPipeline.size());
        var query = new Query().with(pageRequest);
        List<UserSimple> aggregationResult = template.aggregate(aggregation, "user", UserSimple.class).getMappedResults();
        long totalPageCount = template.count(query.limit(0).skip(0), AppUser.class ); //TODO: This makes the code highly inefficient. Also incompatible with match operations.
        var userPage = new PageImpl<>(aggregationResult, pageRequest, totalPageCount);

        log.info("Returning page...");
        return userPage;
    }


}
