package com.web.backend.model.job;

import com.web.backend.dto.ClientSimple;
import com.web.backend.dto.EmployeeSimple;
import com.web.backend.model.jobService.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "job") @Data
@NoArgsConstructor @AllArgsConstructor
public class Job {
    @Id
    private String jobId;
    private String createdBy;
    private String lastUpdatedBy;
    private Review review;
    private Service service;
    private Invoice invoice;
    private Payment payment;
    private ClientSimple client;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
    private List<EmployeeSimple> crewList;

    public Job(String createdBy, String lastUpdatedBy,
               Review review, Service service, Invoice invoice,
               Payment payment, ClientSimple client,
               LocalDateTime startTime, LocalDateTime endTime,
               LocalDateTime createdAt, LocalDateTime lastUpdatedAt,
               List<EmployeeSimple> crewList) {
        this.createdBy = createdBy;
        this.lastUpdatedBy = lastUpdatedBy;
        this.review = review;
        this.service = service;
        this.invoice = invoice;
        this.payment = payment;
        this.client = client;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
        this.crewList = crewList;
    }
}
