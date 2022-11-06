package com.web.backend.dto.crewAssignment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor @NoArgsConstructor @Data
public class ZoneExtendedData {
    private String id; //Actual id
    private String sign; //max 5 character sign
    private String name; //A small name
    private String description; //Description
    private float averageRating = 0;
    private int employeeCount = 0;
    private int pendingJobCount = 0;
    private int completedJobCount = 0;
    private LocalDate createdOn;
    private LocalDate lastUpdatedOn;
}
