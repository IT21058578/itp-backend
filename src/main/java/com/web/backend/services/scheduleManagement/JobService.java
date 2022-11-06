package com.web.backend.services.scheduleManagement;

import com.web.backend.dto.schedManagement.Calender;
import com.web.backend.dto.schedManagement.JobSearchSortParameters;
import com.web.backend.dto.schedManagement.SimplifiedJobSearchSortParameters;
import com.web.backend.dto.schedManagement.JobSimple;
import com.web.backend.exception.NotFoundException;
import com.web.backend.model.crewAssignment.Employee;
import com.web.backend.model.job.Job;
import com.web.backend.model.job.Schedule;
import com.web.backend.repositories.scheduleManagement.JobRepository;
import com.web.backend.repositories.scheduleManagement.ScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service @AllArgsConstructor @Slf4j
public class JobService {
    private final MongoTemplate template;
    private final JobRepository repo;
    private final ScheduleRepository scheduleRepo;

    public List<Calender> getJobCalender(Integer month, Integer year) {
        //Defining start and end dateTime.
        log.info("Preparing startDateTime and endDateTime...");
        var startDateTime = LocalDateTime.of(
                year, month, 1, 0, 0);
        var endDateTime = LocalDateTime.of(
                year, month, YearMonth.of(year, month).lengthOfMonth(), 23, 59);
        log.info("Built startDateTime and endDateTime, {} , {}", startDateTime, endDateTime);

        //Building query.
        log.info("Building query for jobs...");
        Query query = new Query();
        query.addCriteria(Criteria.where("startTime").gte(startDateTime).lt(endDateTime));
        var jobList = template.find(query, Job.class);

        //Jobs are found and seperated by day.
        log.info("Manipulating received data... size of data is {} ", jobList.size());
        var jobListsPerDay = new ArrayList<List<Job>>(YearMonth.of(year, month).lengthOfMonth());
        while (jobListsPerDay.size() < YearMonth.of(year, month).lengthOfMonth()) {
            jobListsPerDay.add(new ArrayList<>());
        }
        log.info("Length of jobListsPerDay is {} ", jobListsPerDay.size());
        jobList.forEach(job -> jobListsPerDay.get(job.getStartTime().getDayOfMonth() - 1).add(job));
        var calender = jobListsPerDay.stream().map(Calender::new).toList();

        //Now to find schedules
        log.info("Building query for schedules...");
        query = new Query();
        query.addCriteria(Criteria.where("date").gte(startDateTime).lt(endDateTime));
        query.addCriteria(Criteria.where("isActive").is(true));
        var scheduleList = template.find(query, Schedule.class);

        log.info("Manipulating received data... size of data is {}", scheduleList.size());
        var scheduleListsPerDay = new ArrayList<List<Schedule>>(YearMonth.of(year, month).lengthOfMonth());
        while (scheduleListsPerDay.size() < YearMonth.of(year, month).lengthOfMonth()) {
            scheduleListsPerDay.add(new ArrayList<>());
        }
        log.info("Length of scheduleListsPerDay is {} ", scheduleListsPerDay.size());
        scheduleList.forEach(schedule -> scheduleListsPerDay.get(schedule.getDate().getDayOfMonth() - 1).add(schedule));
        for (int i = 0; i < calender.size(); i++) {
            calender.get(i).setScheduleList(scheduleListsPerDay.get(i));
            calender.get(i).setDate(LocalDate.of(year, month, i + 1));
        }

        //Calender list is created according to received data and returned.
        log.info("Returning list...");
        return calender;
    }

    public Page<JobSimple> getSimplifiedJobList(SimplifiedJobSearchSortParameters searchParams) {
        //Making aggregation pipeline.
        log.info("Building aggregation pipeline...");
        var pageRequest = PageRequest.of(searchParams.getPgNum(), searchParams.getPgSize());
        var aggregationPipeline = new ArrayList<AggregationOperation>();

        aggregationPipeline.add(Aggregation.project()
                .andExpression("{$toString : {$getField : {$literal: '_id'}}}").as("tempId") //NECESSARY TO DO ID SEARCHING. WE DO NOT KEEP THIS FIELD IN JOB SIMPLE CLASS THOUGH
                .andExpression("{$size : '$crewList'}").as("crewDeployed")
                .andExpression("{$dateDiff : { startDate: '$startTime', endDate: '$endTime', unit: 'hour'}}").as("hoursWorked")
                .andExpression("date").as("date")
                .andExpression("amount").as("earnings")
                .andExpression("{$getField : { field : {$literal: 'rating'}, input: '$review'}}").as("rating"));
        aggregationPipeline.add(Aggregation.match(Criteria.where("hoursWorked").gt(-1)));

        log.info("Modifying pipeline according to searching parameters...");
        if (!searchParams.getJobId().isBlank()) {
            aggregationPipeline.add(Aggregation.match(Criteria.where("tempId").regex(searchParams.getJobId())));
        }

        switch (searchParams.getDateSelect()) {
            case "after" -> aggregationPipeline.add(Aggregation.match(Criteria.where("date").gt(searchParams.getDate())));
            case "before" -> aggregationPipeline.add(Aggregation.match(Criteria.where("date").lt(searchParams.getDate())));
            case "on" -> aggregationPipeline.add(Aggregation.match(Criteria.where("date").is(searchParams.getDate())));
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
        if (!searchParams.getSortCol().isBlank()) {
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

        log.info("Creating the job simple page...");
        var query = new Query().with(pageRequest);
        List<JobSimple> aggregationResult = template.aggregate(aggregation, "job", JobSimple.class).getMappedResults();
        long totalPageCount = template.count(query.limit(0).skip(0), Job.class); //TODO: This makes the code highly inefficient. Also incompatible with match operations.
        var jobSimplePage = new PageImpl<>(aggregationResult, pageRequest, totalPageCount);

        log.info("Returning page...");
        return jobSimplePage;
    }

    public Page<Job> getJobList(JobSearchSortParameters searchParams) {
        log.info("Getting job page with params : {}", searchParams);

        log.info("Building aggregation pipeline...");
        var pageRequest = PageRequest.of(searchParams.getPgNum(), searchParams.getPgNum());
        var aggregationPipeline = new ArrayList<AggregationOperation>();
        aggregationPipeline.add(Aggregation.match(Criteria.where(""))); //So that error is not thrown later

        if (!searchParams.getZoneId().isBlank()) {
            log.info("zoneId is present");
            aggregationPipeline.add(Aggregation.match(Criteria.where("zoneId").is(searchParams.getZoneId())));
        }

        if (!searchParams.getClientId().isBlank()) {
            log.info("clientId is present");
            aggregationPipeline.add(Aggregation.match(Criteria.where("client._id").is(new ObjectId(searchParams.getClientId()))));
        }

        if (!searchParams.getEmployeeId().isBlank()) {
            log.info("employeeId is present");
            aggregationPipeline.add(Aggregation.match(Criteria.where("crewList")
                    .elemMatch(Criteria.where("_id").is(new ObjectId(searchParams.getEmployeeId())))));
        }

        if (!searchParams.getStatus().isBlank()) {
            log.info("status is present");
            if (searchParams.getStatus().equalsIgnoreCase("COMPLETED")) {
                aggregationPipeline.add(Aggregation.match(Criteria.where("endTime").lt(LocalDateTime.now())));
            } else if (searchParams.getStatus().equalsIgnoreCase("PENDING")) {
                aggregationPipeline.add(Aggregation.match(Criteria.where("endTime").gt(LocalDateTime.now())));
            }
        }

        if (!searchParams.getSortCol().isBlank()) {
            log.info("Modifying pipeline according to sorting parameters...");
            switch (searchParams.getSortDir()) {
                case "asc" -> aggregationPipeline.add(Aggregation.sort(Sort.Direction.ASC, searchParams.getSortCol()));
                case "desc" -> aggregationPipeline.add(Aggregation.sort(Sort.Direction.DESC, searchParams.getSortCol()));
            }
        }

        log.info("Getting total number of elements...");
        var aggregation = Aggregation.newAggregation(aggregationPipeline);
        long totalCount = template.aggregate(aggregation, "job", Job.class).getMappedResults().size();

        if (searchParams.isPaging()) {
            log.info("Limiting query according to page params...");
            aggregationPipeline.add(Aggregation.skip((searchParams.getPgNum() - 1) * searchParams.getPgSize()));
            aggregationPipeline.add(Aggregation.limit(searchParams.getPgSize()));
        }

        log.info("Getting elements...");
        aggregation = Aggregation.newAggregation(aggregationPipeline);
        var jobList = template.aggregate(aggregation, "job" ,Job.class).getMappedResults();
        var jobPage = new PageImpl<>(jobList, pageRequest, totalCount);

        log.info("Returning page...");
        return jobPage;
    }

    public Job getJobPageDetails(String jobId) {
        log.info("Getting details of job with id : {}", jobId);
        return repo.findById(jobId).orElseThrow(NotFoundException::new);
    }

    public void editJob(String jobId, Job job) {
        log.info("Getting details of job with id : {}", jobId);
        Job oldJob = repo.findById(jobId).orElseThrow(NotFoundException::new);

        log.info("Replace oldJob with newJob details...");
        job.setId(oldJob.getId());

        log.info("Saving job...");
        repo.save(job);
    }

    public void deleteJob(String jobId) {
        log.info("Getting details of job with id : {}", jobId);
        Job job = repo.findById(jobId).orElseThrow(NotFoundException::new);

        log.info("Deleting job...");
        repo.deleteById(jobId);
    }
}
