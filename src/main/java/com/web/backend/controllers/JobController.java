package com.web.backend.controllers;

import com.google.common.base.Stopwatch;
import com.web.backend.dto.JobDayStat;
import com.web.backend.dto.JobSimple;
import com.web.backend.dto.JobPeriodStat;
import com.web.backend.model.job.Job;
import com.web.backend.services.JobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/job") @RestController @Slf4j
@AllArgsConstructor
public class JobController {
    private final JobService jobService;
    private final Stopwatch watch = Stopwatch.createUnstarted(); //Performance benchmarking

    @GetMapping(params = {"month", "year"})
    public ResponseEntity<List<JobDayStat>> getJobCalender(@RequestParam Integer month, @RequestParam Integer year) {
        log.info("JobController received GET Request with params {month, year}");
        watch.start();
        var jobCalenderList = jobService.getJobCalender(month, year);
        log.info("GET Request with params {month, year} took {} to process", watch);
        watch.stop();
        watch.reset();
        return ResponseEntity.ok().body(jobCalenderList);
    }

    @GetMapping(params = {"month", "year", "startDay", "endDay"})
    public ResponseEntity<JobPeriodStat> getJobStatistics(@RequestParam Integer month,
                                                          @RequestParam Integer year,
                                                          @RequestParam Integer startDay,
                                                          @RequestParam Integer endDay) {
        log.info("JobController received GET Request with params {month, year, startDay, endDay}");
        watch.start();
        var jobStatistcs = jobService.getJobStatistcs(month, year, startDay, endDay);
        log.info("GET Request with params {month, year} took {} to process", watch);
        watch.stop();
        watch.reset();
        return ResponseEntity.ok().body(jobStatistcs);
    }

    //TODO: Resolve Search Params
    @GetMapping(params = {"pgNum", "pgSize"})
    public ResponseEntity<Page<JobSimple>> getJobList(@RequestParam(defaultValue = "1") Integer pgNum,
                                                      @RequestParam(defaultValue = "10") Integer pgSize) {
        log.info("JobController received GET Request with params {pgNum, pgSize, ...}");
        watch.start();
        var jobSimplePage = jobService.getJobList(pgNum, pgSize);
        log.info("GET Request with params {month, year} took {} to process", watch);
        watch.stop();
        watch.reset();
        return ResponseEntity.ok().body(jobSimplePage);
    }

    @GetMapping(params = {"jobId"})
    public ResponseEntity<Job> getJobDetails(@RequestParam String jobId) {
        log.info("JobController received GET Request with params {jobId}");
        return ResponseEntity.ok().body(jobService.getJobDetails(jobId));

    }

    public ResponseEntity<?> editJob(@RequestParam String jobId, @RequestBody Job job) {
        log.info("JobController received PUT Request with params {jobId} and body {job}");
        jobService.editJob(jobId, job);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteJob(@RequestParam String jobId) {
        log.info("JobController received DELETE Request with params {jobId}");
        jobService.deleteJob(jobId);
        return ResponseEntity.ok().build();
    }
}
