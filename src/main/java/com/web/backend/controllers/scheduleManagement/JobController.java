package com.web.backend.controllers.scheduleManagement;

import com.web.backend.dto.schedManagement.JobSearchSortParameters;
import com.web.backend.dto.userManagement.UserJobSearchSortParameters;
import com.web.backend.model.job.Job;
import com.web.backend.services.scheduleManagement.JobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/job") @RestController @Slf4j
@AllArgsConstructor @CrossOrigin
public class JobController {
    private final JobService jobService;

    @GetMapping(params = {"month", "year"})
    public ResponseEntity<?> getJobCalender(@RequestParam Integer month, @RequestParam Integer year) {
        log.info("JobController received GET Request with params {month, year}");
        var jobCalenderList = jobService.getJobCalender(month, year);
        return ResponseEntity.ok().body(jobCalenderList);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<?> getJobList(@RequestBody JobSearchSortParameters searchParams) {
        log.info("JobController received GET Request with params {pgNum, pgSize, ...}");
        var jobSimplePage = jobService.getJobList(searchParams);
        return ResponseEntity.ok().body(jobSimplePage);
    }

    @PostMapping(value = "/client")
    public ResponseEntity<?> getClientJobsList(@RequestBody UserJobSearchSortParameters searchParams) {
        log.info("JobController received GET Request with params {userId} for completed");
        var jobPage = jobService.getClientJobsList(searchParams);
        return ResponseEntity.ok().body(jobPage);
    }

    @GetMapping(params = {"jobId"})
    public ResponseEntity<?> getJobPageDetails(@RequestParam String jobId) {
        log.info("JobController received GET Request with params {jobId}");
        return ResponseEntity.ok().body(jobService.getJobPageDetails(jobId));
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
