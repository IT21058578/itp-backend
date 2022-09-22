package com.web.backend.dto;

import com.web.backend.model.job.Job;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor @Data
public class JobSimple {
    private long hoursWorked;
    private int crewDeployed;
    private float rating;
    private String id;
    private LocalDate date;
    private double earnings;
    private List<String> employeeIds;

    public JobSimple (Job job) {
        this.id = job.getJobId();
        this.employeeIds =  job.getCrewList() != null ?
                job.getCrewList().stream().map(EmployeeSimple::getId).toList()
                : Collections.emptyList();
        this.date = job.getStartTime().toLocalDate();
        this.crewDeployed = job.getCrewList() != null ? job.getCrewList().size() : 0;
        this.rating = job.getReview() != null ? job.getReview().getRating() : 0;
        this.earnings = job.getInvoice() != null ? job.getInvoice().getTotal() : 0;
        this.hoursWorked = Math.abs(Duration.between(job.getStartTime(), job.getEndTime()).toHours());
    }
}
