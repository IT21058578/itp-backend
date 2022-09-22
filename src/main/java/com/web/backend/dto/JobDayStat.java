package com.web.backend.dto;

import com.web.backend.model.job.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class JobDayStat {
    private int jobsWorked;
    private int crewDeployed;
    private long hoursWorked;
    private float avgRating;
    private double earnings;
    private LocalDate day;

    public JobDayStat(List<Job> jobs) {
        int jobsWorked = 0;
        int crewDeployed = 0;
        long hoursWorked = 0;
        float totalRating = 0;
        double earnings = 0;
        LocalDate day = null; //TODO: This null may cause issues when list is empty.

        if (!jobs.isEmpty()) {
            jobsWorked = jobs.size();
            day = jobs.get(0).getStartTime().toLocalDate();

            for (Job job : jobs) {
                crewDeployed += job.getCrewList() != null ? job.getCrewList().size() : 0;
                hoursWorked += Math.abs(Duration.between(job.getStartTime(), job.getEndTime()).toHours());
                totalRating += job.getReview() != null ? job.getReview().getRating() : 0;
                earnings += job.getInvoice() != null ? job.getInvoice().getTotal() : 0; //TODO: Ensure this is correct value when in production.
            }
        }

        this.crewDeployed = crewDeployed;
        this.hoursWorked = hoursWorked;
        this.avgRating = totalRating / jobsWorked;
        this.jobsWorked = jobsWorked;
        this.earnings = earnings;
        this.day = day;
    }
}
