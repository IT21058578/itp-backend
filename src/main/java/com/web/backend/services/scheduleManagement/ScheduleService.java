package com.web.backend.services.scheduleManagement;

import com.web.backend.dto.schedManagement.ScheduleSearchSortParameters;
import com.web.backend.exception.NotFoundException;
import com.web.backend.model.job.Schedule;
import com.web.backend.repositories.scheduleManagement.ScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @AllArgsConstructor @Slf4j
public class ScheduleService {
    private final MongoTemplate template;
    private final ScheduleRepository repository;

    public List<Schedule> getScheduleList() {
        log.info("Getting all schedule details... ");
        return repository.findAll();
    }

    public Page<Schedule> getScheduleListPage(Integer pgSize, Integer pgNum) {
        log.info("Preparing query...");
        var pageRequest = PageRequest.of(pgNum, pgSize);
        var query = new Query().with(pageRequest);

        log.info("Getting page...");
        var schedulePage = PageableExecutionUtils.getPage(
                template.find(query, Schedule.class),
                pageRequest,
                () -> template.count(query.skip(0).limit(0), Schedule.class));

        log.info("Returning page...");
        return schedulePage;
    }

    public void postSchedule(Schedule schedule) {
        log.info("Creating new schedule with details: {} ...", schedule);
        repository.save(schedule);
    }

    public void editSchedule(String id, Schedule schedule) {
        log.info("Getting details of schedule with id {} ...", id);
        Schedule oldSchedule = repository.findById(id).orElseThrow(NotFoundException::new);
        log.info("Replacing old details with new details...");
        schedule.setId(oldSchedule.getId());
        log.info("Saving new schedule details...");
        repository.save(schedule);
    }

    public void deleteSchedule(String id) {
        log.info("Deleting schedule with id {} ...", id);
        repository.deleteById(id);
    }

    public Page<Schedule> getScheduleListPageWithSearch(ScheduleSearchSortParameters searchParams) {
        log.info("Building aggregation pipeline...");
        var pageRequest = PageRequest.of(searchParams.getPgNum(), searchParams.getPgNum());
        var aggregationPipeline = new ArrayList<AggregationOperation>();

        log.info("Modifying pipeline according to search params...");
        switch(searchParams.getShowSelect()) {
            case "active" -> aggregationPipeline.add(Aggregation.match(Criteria.where("isActive").is(true)));
            case "inActive" -> aggregationPipeline.add(Aggregation.match(Criteria.where("isActive").is(false)));
        }

        switch(searchParams.getSearchSelect()) {
            case "title" -> aggregationPipeline.add(Aggregation.match(Criteria.where("title").regex(searchParams.getSearchString(), "i")));
            case "desc" -> aggregationPipeline.add(Aggregation.match(Criteria.where("description").regex(searchParams.getSearchString(), "i")));
            case "both" -> {}
        }

        switch(searchParams.getDateSelect()) {
            case "after" -> aggregationPipeline.add(Aggregation.match(Criteria.where("date").gt(searchParams.getDate())));
            case "before" -> aggregationPipeline.add(Aggregation.match(Criteria.where("date").lt(searchParams.getDate())));
            case "on" -> aggregationPipeline.add(Aggregation.match(Criteria.where("date").is(searchParams.getDate())));
        }

        switch(searchParams.getStartTimeSelect()) {
            case "after" -> aggregationPipeline.add(Aggregation.match(Criteria.where("startTime").gt(searchParams.getStartTime())));
            case "before" -> aggregationPipeline.add(Aggregation.match(Criteria.where("startTime").lt(searchParams.getStartTime())));
            case "on" -> aggregationPipeline.add(Aggregation.match(Criteria.where("startTime").is(searchParams.getStartTime())));
        }

        switch(searchParams.getEndTimeSelect()) {
            case "after" -> aggregationPipeline.add(Aggregation.match(Criteria.where("endTime").gt(searchParams.getEndTime())));
            case "before" -> aggregationPipeline.add(Aggregation.match(Criteria.where("endTime").lt(searchParams.getEndTime())));
            case "on" -> aggregationPipeline.add(Aggregation.match(Criteria.where("endTime").is(searchParams.getEndTime())));
        }

        log.info("Modifying pipeline according to sort params...");
        if (!searchParams.getSortCol().isBlank()) {
            switch (searchParams.getSortDir()) {
                case "asc" -> aggregationPipeline.add(Aggregation.sort(Sort.Direction.ASC, searchParams.getSortCol()));
                case "desc" -> aggregationPipeline.add(Aggregation.sort(Sort.Direction.DESC, searchParams.getSortCol()));
            }
        }

        log.info("Limiting query according to page params...");
        aggregationPipeline.add(Aggregation.skip((searchParams.getPgNum() - 1) * searchParams.getPgSize()));
        aggregationPipeline.add(Aggregation.limit(searchParams.getPgSize()));

        log.info("Finalizing pipeline...");
        var aggregation = Aggregation.newAggregation(aggregationPipeline);

        log.info("Getting page...");
        var query = new Query().with(pageRequest);
        List<Schedule> scheduleList = template.aggregate(aggregation, "schedule" ,Schedule.class).getMappedResults();
        long totalScheduleCount = 100; //TODO: Resolve hardcoded issue.
        Page<Schedule> schedulePage = new PageImpl<Schedule>( scheduleList, pageRequest, totalScheduleCount);

        log.info("Returning page...");
        return schedulePage;
    }
}
