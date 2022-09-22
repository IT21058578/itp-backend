package com.web.backend.services;

import com.web.backend.dto.JobDayStat;
import com.web.backend.dto.JobSimple;
import com.web.backend.dto.JobPeriodStat;
import com.web.backend.exception.NotFoundException;
import com.web.backend.model.job.Job;
import com.web.backend.repositories.JobRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
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

    public List<JobDayStat> getJobCalender(Integer month, Integer year) {
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

        //JobDayStat list is created according to received data and returned.
        log.info("Returning list...");
        return jobListsPerDay.stream().map(JobDayStat::new).toList();
    }

    public Page<JobSimple> getJobList(int pgNum, int pgSize) {
        //TODO: Add support for searching by: Time, jobId, crewNum, earnings, etc.
        log.info("Preparing variables...");
        //Variables should be prepared here.

        //Building query.
        log.info("Building query...");
        var pageRequest = PageRequest.of(pgNum, pgSize);
        var query = new Query().with(pageRequest);

        log.info("Adding Criteria...");
        //Criteria should be added here.

        log.info("Creating the job page...");
        var jobPage =  PageableExecutionUtils.getPage(
                template.find(query, Job.class),
                pageRequest,
                () -> template.count(query.skip(0).limit(0), Job.class));

        log.info("Converting job page to jobSimple page...");
        var jobSimplePage = new PageImpl<JobSimple>(
                jobPage.getContent().stream().map(JobSimple::new).toList(),
                pageRequest,
                jobPage.getTotalElements());

        log.info("Returning page...");
        return jobSimplePage;
    }

    public Job getJobDetails(String jobId) {
        log.info("Getting details of job with id : {}", jobId);
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
}
