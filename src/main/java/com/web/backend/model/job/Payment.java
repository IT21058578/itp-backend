package com.web.backend.model.job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor
public class Payment {
    //private String id; //No id as stored with job
    private String status;
    private String city;
    private String country;
    private String address;
    private double amount;
    private boolean isRefunded;
    private LocalDateTime refundedAt;
    private LocalDateTime createdAt; //Combine payment date and time.
    private int contactNo;
}
