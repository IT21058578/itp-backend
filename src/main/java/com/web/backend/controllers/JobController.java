package com.web.backend.controllers;

import com.web.backend.dto.JobCalender;
import com.web.backend.dto.JobSimple;
import com.web.backend.dto.JobStatistics;
import com.web.backend.model.job.Job;
import com.web.backend.services.JobService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/job") @RestController
@AllArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping(params = {"month", "year"})
    public ResponseEntity<List<JobCalender>> getJobCalender(@RequestParam Integer month, @RequestParam Integer year) {
        return ResponseEntity.ok().body(jobService.getJobCalender(month, year));
    }

    @GetMapping(params = {"month", "year", "startDay", "endDay"})
    public ResponseEntity<JobStatistics> getJobStatistics(@RequestParam Integer month,
                                                          @RequestParam Integer year,
                                                          @RequestParam Integer startDay,
                                                          @RequestParam Integer endDay) {
        return ResponseEntity.ok().body(jobService.getJobStatistcs(month, year, startDay, endDay));
    }

    //TODO: Resolve Search Params
    @GetMapping(params = {"pgNum", "pgSize"})
    public ResponseEntity<Page<JobSimple>> getJobList(@RequestParam(defaultValue = "1") Integer pgNum,
                                                      @RequestParam(defaultValue = "10") Integer pgSize) {
        return ResponseEntity.ok().body(jobService.getJobList(pgNum, pgSize));
    }

    @GetMapping(params = {"jobId"})
    public ResponseEntity<Job> getJobDetails(@RequestParam String jobId) {
        return ResponseEntity.ok().body(jobService.getJobDetails(jobId));

    }

    public ResponseEntity<?> editJob(@RequestParam String jobId, @RequestBody Job job) {
        jobService.editJob(jobId, job);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteJob(@RequestParam String jobId) {
        jobService.deleteJob(jobId);
        return ResponseEntity.ok().build();
    }
}
