package com.web.backend.model.job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor @NoArgsConstructor
@Document(collection="schedule") @Data
public class Schedule {
    private String id;
    private String title;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;
    private boolean isActive;

    public Schedule(String title, LocalDate date, LocalTime startTime, LocalTime endTime, String description, boolean isActive) {
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.isActive = isActive;
    }
}
