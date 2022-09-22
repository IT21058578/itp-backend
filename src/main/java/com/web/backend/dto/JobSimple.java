package com.web.backend.dto;

import com.web.backend.model.job.Job;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor @Data
public class JobSimple {
    private long hoursWorked;
    private int crewDeployed;
    private float rating;
    private String id;
    private double earnings;
    private List<String> employeeIds;

    public JobSimple (Job job) {
        this.id = job.getJobId();
        this.employeeIds = job.getCrewList().stream().map(EmployeeSimple::getId).toList();
        this.crewDeployed = job.getCrewList().size();
        this.rating = job.getReview().getRating();
        this.earnings = job.getInvoice().getTotal();
        this.hoursWorked = Math.abs(Duration.between(job.getStartTime(), job.getEndTime()).toHours());
    }
}
