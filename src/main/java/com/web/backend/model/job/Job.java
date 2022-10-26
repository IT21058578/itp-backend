package com.web.backend.model.job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data
public class Job {
    @Id
    private String id;
    private String zone;
    private String address;
    private String paymentId;
    private String invoiceId;
    private float amount;
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;
    private List<JobCrewMemberSimple> crewList;
    private List<JobServiceSimple> serviceList;
    private JobClientSimple client;
    private Review review;

    @AllArgsConstructor @Data
    public static class JobCrewMemberSimple {
        private String id;
        private String firstName;
        private String lastName;
    }

    @AllArgsConstructor @Data
    public static class JobServiceSimple {
        private String id;
        private String name;
        private float cost;
        private int quantity;
    }

    @AllArgsConstructor @Data
    public static class JobClientSimple {
        private String id;
        private String firstName;
        private String lastName;
        private String email;
        private String mobile;
    }
}
