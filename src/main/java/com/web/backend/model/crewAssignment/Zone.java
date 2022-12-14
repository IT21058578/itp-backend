package com.web.backend.model.crewAssignment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
public class Zone {
    private String id; //Actual id
    private String sign; //max 5 character sign
    private String name; //A small name
    private String description; //Description
    private LocalDate createdOn;
    private LocalDate lastUpdatedOn;
    private boolean disabled;
}
