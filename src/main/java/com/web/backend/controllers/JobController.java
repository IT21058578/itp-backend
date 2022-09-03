package com.web.backend.controllers;

import com.web.backend.dto.JobCalender;
import com.web.backend.dto.JobSimple;
import com.web.backend.dto.JobStatistics;
import com.web.backend.model.job.Job;
import com.web.backend.services.JobService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/job") @RestController
@AllArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping("/calender")
    public ResponseEntity<JobCalender> getJobCalender(@RequestParam Integer month,
                                                      @RequestParam Integer year) {
        return ResponseEntity.ok().body(jobService.getJobCalender(month, year));
    }

    @GetMapping("/stat")
    public ResponseEntity<JobStatistics> getJobStatistics(@RequestParam Integer month,
                                                          @RequestParam Integer year,
                                                          @RequestParam Integer startDay,
                                                          @RequestParam Integer endDay) {
        return ResponseEntity.ok().body(jobService.getJobStatistcs(month, year, startDay, endDay));
    }

    //TODO: Resolve Search Params
    @GetMapping("/list")
    public ResponseEntity<List<JobSimple>> getJobList() {
        return ResponseEntity.ok().body(jobService.getJobList());
    }

    @GetMapping("/single")
    public ResponseEntity<Job> getJobDetails(@RequestParam Integer jobId) {
        return ResponseEntity.ok().body(jobService.getJobDetails(jobId));

    }

    public void editJob() {
        jobService.editJob();
    }

    public void deleteJob() {
        jobService.deleteJob();
    }
}
