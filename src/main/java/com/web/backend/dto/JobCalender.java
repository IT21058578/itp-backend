package com.web.backend.dto;

import com.web.backend.model.job.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class JobCalender {
    private int crewDeployed;
    private long hoursWorked;
    private int jobsWorked;
    private float avgRating;
    private double earnings;
    private LocalDate day;

    //This function receives a set of jobs sorted by day.
    public JobCalender(List<Job> jobs) {
        if (!jobs.isEmpty()) {
            int jobsWorked = jobs.size();
            int crewDeployed = 0;
            long hoursWorked = 0;
            float totalRating = 0;
            double earnings = 0;
            LocalDate day = jobs.get(0).getCreatedAt().toLocalDate();

            for (Job job : jobs) {
                crewDeployed += job.getCrewList().size();
                hoursWorked += Math.abs(Duration.between(job.getStartTime(), job.getEndTime()).toHours());
                totalRating += job.getReview().getRating();
                earnings += job.getInvoice().getTotal(); //TODO: Ensure this is correct value when in production.
            }

            this.crewDeployed = crewDeployed;
            this.hoursWorked = hoursWorked;
            this.avgRating = totalRating / jobsWorked;
            this.jobsWorked = jobsWorked;
            this.earnings = earnings;
            this.day = day;
        } else {
            this.crewDeployed = 0;
            this.hoursWorked = 0;
            this.jobsWorked = 0;
            this.avgRating = 0;
            this.earnings = 0;
            this.day = null; //TODO: This null may cause issues.
        }
    }
}
