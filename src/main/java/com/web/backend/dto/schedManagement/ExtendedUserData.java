package com.web.backend.dto.schedManagement;

import com.web.backend.model.job.Job;
import com.web.backend.model.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class ExtendedUserData {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String userType;
    private boolean isEmailsAccepted;
    private int totalJobCount = 0;
    private int pendingJobCount = 0;
    private int completedJobCount = 0;
    private int reviewsLeft = 0;
    private float totalEarnings = 0;
    private float averageEarnings = 0;
    private float totalRatings = 0;
    private float averageRating = 0;
    private LocalDate dateOfBirth;
    private LocalDateTime createdAt;
    private LocalDateTime lastLoggedAt;

    public ExtendedUserData(AppUser user, List<Job> jobList) {
        //Getting user details
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.userType = user.getPermissions().get(0);
        this.dateOfBirth = user.getDateOfBirth();
        this.createdAt = user.getCreatedAt();
        this.lastLoggedAt = user.getLastLoggedAt();
        this.isEmailsAccepted = user.isEmailsAccepted();
        this.mobile = user.getMobile();

        //Getting job details
        this.totalJobCount = jobList.size();
        for (Job job: jobList) {
            if (job.getStartTime().isBefore(LocalDateTime.now())) { completedJobCount++; }
            else { pendingJobCount++; }
            if(job.getReview() != null) {
                reviewsLeft++;
                totalRatings += job.getReview().getRating();
            }
            totalEarnings = job.getAmount();
        }
        this.averageEarnings = totalEarnings / this.totalJobCount;
        this.averageRating = totalRatings / this.totalJobCount;
    }
}
