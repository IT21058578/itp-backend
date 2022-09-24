package com.web.backend.services;

import com.web.backend.exception.NotFoundException;
import com.web.backend.model.job.Schedule;
import com.web.backend.repositories.ScheduleRepository;
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

import java.time.LocalDate;
import java.time.LocalTime;
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
        log.info("Creating new employee with details: {} ...", schedule);
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

    public Page<Schedule> getScheduleListPageWithSearch(Integer pgSize, Integer pgNum, String searchSelect, String searchString, String dateSelect, LocalDate date, String startTimeSelect, LocalTime startTime, String endTimeSelect, LocalTime endTime, String showSelect, String sortDir, String sortCol) {
        log.info("Building aggregation pipeline...");
        var pageRequest = PageRequest.of(pgNum, pgSize);
        var aggregationPipeline = new ArrayList<AggregationOperation>();

        log.info("Modifying pipeline according to search params...");
        switch(showSelect) {
            case "active" -> aggregationPipeline.add(Aggregation.match(Criteria.where("isActive").is(true)));
            case "inActive" -> aggregationPipeline.add(Aggregation.match(Criteria.where("isActive").is(false)));
        }

        switch(searchSelect) {
            case "title" -> aggregationPipeline.add(Aggregation.match(Criteria.where("title").regex(searchString)));
            case "desc" -> aggregationPipeline.add(Aggregation.match(Criteria.where("description").regex(searchString)));
            case "both" -> {}
        }

        switch(dateSelect) {
            case "after" -> aggregationPipeline.add(Aggregation.match(Criteria.where("date").gt(date)));
            case "before" -> aggregationPipeline.add(Aggregation.match(Criteria.where("date").lt(date)));
            case "on" -> aggregationPipeline.add(Aggregation.match(Criteria.where("date").is(date)));
        }

        switch(startTimeSelect) {
            case "after" -> aggregationPipeline.add(Aggregation.match(Criteria.where("startTime").gt(startTime)));
            case "before" -> aggregationPipeline.add(Aggregation.match(Criteria.where("startTime").lt(startTime)));
            case "on" -> aggregationPipeline.add(Aggregation.match(Criteria.where("startTime").is(startTime)));
        }

        switch(endTimeSelect) {
            case "after" -> aggregationPipeline.add(Aggregation.match(Criteria.where("endTime").gt(endTime)));
            case "before" -> aggregationPipeline.add(Aggregation.match(Criteria.where("endTime").lt(endTime)));
            case "on" -> aggregationPipeline.add(Aggregation.match(Criteria.where("endTime").is(endTime)));
        }

        log.info("Modifying pipeline according to sort params...");
        if (!sortCol.isBlank()) {
            switch (sortDir) {
                case "asc" -> aggregationPipeline.add(Aggregation.sort(Sort.Direction.ASC, sortCol));
                case "desc" -> aggregationPipeline.add(Aggregation.sort(Sort.Direction.DESC, sortCol));
            }
        }

        log.info("Limiting query according to page params...");
        aggregationPipeline.add(Aggregation.skip(pgSize * pgNum));
        aggregationPipeline.add(Aggregation.limit(pgSize));

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
