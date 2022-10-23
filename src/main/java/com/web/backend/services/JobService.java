package com.web.backend.services;

import com.web.backend.dto.schedManagement.Calender;
import com.web.backend.dto.schedManagement.JobSearchSortParameters;
import com.web.backend.dto.schedManagement.JobSimple;
import com.web.backend.exception.NotFoundException;
import com.web.backend.repositories.JobRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

//TODO: Make all functions
@Service @AllArgsConstructor @Slf4j
public class JobService {
    private final MongoTemplate template;
    private final JobRepository repo;

    public List<Calender> getJobCalender(Integer month, Integer year) {
        //Defining start and end dateTime.
        log.info("Preparing startDateTime and endDateTime...");
        var startDateTime = LocalDateTime.of(
                year, month, 1, 0, 0);
        var endDateTime = LocalDateTime.of(
                year, month, YearMonth.of(year, month).lengthOfMonth(), 23 ,59);
        log.info("Built startDateTime and endDateTime, {} , {}", startDateTime, endDateTime);

        //Building query.
        log.info("Building query...");
        Query query = new Query();
        query.addCriteria(Criteria.where("startTime").gte(startDateTime).lt(endDateTime));
        var jobList = template.find(query, Job.class);

        //Jobs are found and seperated by day.
        log.info("Manipulating received data... size of data is {} ", jobList.size());
        var jobListsPerDay = new ArrayList<List<Job>>(YearMonth.of(year,month).lengthOfMonth());
        while (jobListsPerDay.size() < YearMonth.of(year,month).lengthOfMonth()) {
            jobListsPerDay.add(new ArrayList<Job>());
        }
        log.info("Length of jobListsPerDay is {} ", jobListsPerDay.size());
        jobList.forEach(job -> jobListsPerDay.get(job.getStartTime().getDayOfMonth() - 1).add(job));

        //Calender list is created according to received data and returned.
        log.info("Returning list...");
        return jobListsPerDay.stream().map(Calender::new).toList();
    }

    public Page<JobSimple> getJobList(JobSearchSortParameters searchParams) {
        //Making aggregation pipeline.
        log.info("Building aggregation pipeline...");
        var pageRequest = PageRequest.of(searchParams.getPgNum(), searchParams.getPgSize());
        var aggregationPipeline = new ArrayList<AggregationOperation>();
        aggregationPipeline.add(Aggregation.project()
                        .andExpression("{$toString : {$getField : {$literal: '_id'}}}").as("tempId") //NECESSARY TO DO ID SEARCHING. WE DO NOT KEEP THIS FIELD IN JOB SIMPLE CLASS THOUGH
                        .andExpression("{$size : '$crewList'}").as("crewDeployed")
                        .andExpression("{$dateDiff : { startDate: '$startTime', endDate: '$endTime', unit: 'hour'}}").as("hoursWorked")
                        .andExpression("{$getField : {$literal: 'startTime'}}").as("date")
                        .andExpression("{$getField : { field : {$literal: 'total'}, input: '$invoice'}}").as("earnings")
                        .andExpression("{$getField : { field : {$literal: 'rating'}, input: '$review'}}").as("rating"));

        log.info("Modifying pipeline according to searching parameters...");
        if (!searchParams.getJobId().isBlank()) {
            aggregationPipeline.add(Aggregation.match(Criteria.where("tempId").regex(searchParams.getJobId())));
        }

        switch (searchParams.getLengthSelect()) {
            case "lessThan" -> aggregationPipeline.add(Aggregation.match(Criteria.where("hoursWorked").lt(searchParams.getLength())));
            case "greaterThan" -> aggregationPipeline.add(Aggregation.match(Criteria.where("hoursWorked").gt(searchParams.getLength())));
            case "equal" -> aggregationPipeline.add(Aggregation.match(Criteria.where("hoursWorked").is(searchParams.getLength())));
        }
        switch (searchParams.getCrewSelect()) {
            case "lessThan" -> aggregationPipeline.add(Aggregation.match(Criteria.where("crewDeployed").lt(searchParams.getCrew())));
            case "greaterThan" -> aggregationPipeline.add(Aggregation.match(Criteria.where("crewDeployed").gt(searchParams.getCrew())));
            case "equal" -> aggregationPipeline.add(Aggregation.match(Criteria.where("crewDeployed").is(searchParams.getCrew())));
        }
        switch (searchParams.getRevenueSelect()) {
            case "lessThan" -> aggregationPipeline.add(Aggregation.match(Criteria.where("earnings").lt(searchParams.getRevenue())));
            case "greaterThan" -> aggregationPipeline.add(Aggregation.match(Criteria.where("earnings").gt(searchParams.getRevenue())));
            case "equal" -> aggregationPipeline.add(Aggregation.match(Criteria.where("earnings").is(searchParams.getRevenue())));
        }
        switch (searchParams.getRatingSelect()) {
            case "lessThan" -> aggregationPipeline.add(Aggregation.match(Criteria.where("rating").lt(searchParams.getRating())));
            case "greaterThan" -> aggregationPipeline.add(Aggregation.match(Criteria.where("rating").gt(searchParams.getRating())));
            case "equal" -> aggregationPipeline.add(Aggregation.match(Criteria.where("rating").is(searchParams.getRating())));
        }

        log.info("Modifying pipeline according to sorting parameters...");
        if(!searchParams.getSortCol().isBlank()) {
            switch (searchParams.getSortDir()) {
                case "asc" -> aggregationPipeline.add(Aggregation.sort(Sort.Direction.ASC, searchParams.getSortCol()));
                case "desc" -> aggregationPipeline.add(Aggregation.sort(Sort.Direction.DESC, searchParams.getSortCol()));
            }
        }

        log.info("Limiting search according to page parameters...");
        aggregationPipeline.add(Aggregation.skip(pageRequest.getPageNumber() * pageRequest.getPageSize())); //TODO: Enable this once infinite scrolling is implemented.
        aggregationPipeline.add(Aggregation.limit(pageRequest.getPageSize()));

        log.info("Finalizing aggregation pipeline...");
        var aggregation = Aggregation.newAggregation(aggregationPipeline);

        log.info("Creating the job simple page...");
        var query = new Query().with(pageRequest);
        List<JobSimple> aggregationResult = template.aggregate(aggregation, "job", JobSimple.class).getMappedResults();
        long totalPageCount = template.count(query.limit(0).skip(0), Job.class ); //TODO: This makes the code highly inefficient. Also incompatible with match operations.
        var jobSimplePage = new PageImpl<JobSimple>(aggregationResult, pageRequest, totalPageCount);

        log.info("Returning page...");
        return jobSimplePage;
    }

    public Job getJobPageDetails(String jobId) {
        log.info("Getting details of job with id : {}", jobId);
        //TODO: Get all relevant info and create job page.
        return repo.findById(jobId).orElseThrow(NotFoundException::new);
    }

    public void editJob(String jobId, Job job) {
        log.info("Getting details of job with id : {}", jobId);
        Job oldJob = repo.findById(jobId).orElseThrow(NotFoundException::new);

        log.info("Replace oldJob with newJob details...");
        job.setJobId(oldJob.getJobId());

        log.info("Saving job...");
        repo.save(job);
    }

    public void deleteJob(String jobId) {
        log.info("Getting details of job with id : {}", jobId);
        Job job = repo.findById(jobId).orElseThrow(NotFoundException::new);

        log.info("Deleting job...");
        repo.deleteById(jobId);
    }

    public Page<Job> getClientCompletedJobsList(String id) {
        return null;
    }

    public Page<Job> getClientFutureJobsList(String id) {
        return null;
    }
}
