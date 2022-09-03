package com.web.backend.services;

import com.web.backend.dto.JobCalender;
import com.web.backend.dto.JobSimple;
import com.web.backend.dto.JobStatistics;
import com.web.backend.model.job.Job;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//TODO: Make all functions
@Service
public class JobService {
    public JobCalender getJobCalender(Integer month, Integer year) {
        return new JobCalender();
    }

    public JobStatistics getJobStatistcs(Integer month, Integer year, Integer startDay, Integer endDay) {
        return new JobStatistics();
    }

    public List<JobSimple> getJobList() {
        return new ArrayList<>();
    }

    public Job getJobDetails(Integer jobId) {
        return new Job();
    }

    public void editJob() {
    }

    public void deleteJob() {
    }
}
