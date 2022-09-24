package com.web.backend.controllers;

import com.web.backend.dto.JobDayStat;
import com.web.backend.dto.JobSimple;
import com.web.backend.model.job.Job;
import com.web.backend.services.JobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/job") @RestController @Slf4j
@AllArgsConstructor @CrossOrigin
public class JobController {
    private final JobService jobService;

    @GetMapping(params = {"month", "year"})
    public ResponseEntity<List<JobDayStat>> getJobCalender(@RequestParam Integer month, @RequestParam Integer year) {
        log.info("JobController received GET Request with params {month, year}");
        var jobCalenderList = jobService.getJobCalender(month, year);
        return ResponseEntity.ok().body(jobCalenderList);
    }

    //TODO: Resolve Search Params
    @GetMapping(params = {"pgNum", "pgSize"})
    public ResponseEntity<Page<JobSimple>> getJobList(@RequestParam(defaultValue = "1") Integer pgNum,
                                                      @RequestParam(defaultValue = "10") Integer pgSize,
                                                      @RequestParam(required = false, defaultValue = "") String jobId,
                                                      @RequestParam(required = false, defaultValue = "ignore") String lengthSelect,
                                                      @RequestParam(required = false, defaultValue = "0") Integer length,
                                                      @RequestParam(required = false, defaultValue = "ignore") String crewSelect,
                                                      @RequestParam(required = false, defaultValue = "0") Integer crew,
                                                      @RequestParam(required = false, defaultValue = "ignore") String revenueSelect,
                                                      @RequestParam(required = false, defaultValue = "0") Double revenue,
                                                      @RequestParam(required = false, defaultValue = "ignore") String ratingSelect,
                                                      @RequestParam(required = false, defaultValue = "0") Double rating,
                                                      @RequestParam(required = false, defaultValue = "") String sortCol,
                                                      @RequestParam(required = false, defaultValue = "") String sortDir) {
        log.info("JobController received GET Request with params {pgNum, pgSize, ...}");
        var jobSimplePage = jobService.getJobList(pgNum, pgSize, jobId,
                lengthSelect, length, crewSelect, crew,
                revenueSelect, revenue, ratingSelect, rating,
                sortCol, sortDir);
        return ResponseEntity.ok().body(jobSimplePage);
    }

    @GetMapping(params = {"jobId"})
    public ResponseEntity<Job> getJobDetails(@RequestParam String jobId) {
        log.info("JobController received GET Request with params {jobId}");
        return ResponseEntity.ok().body(jobService.getJobDetails(jobId));
    }

    @PutMapping
    public ResponseEntity<?> editJob(@RequestParam String jobId, @RequestBody Job job) {
        log.info("JobController received PUT Request with params {jobId} and body {job}");
        jobService.editJob(jobId, job);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteJob(@RequestParam String jobId) {
        log.info("JobController received DELETE Request with params {jobId}");
        jobService.deleteJob(jobId);
        return ResponseEntity.ok().build();
    }
}
