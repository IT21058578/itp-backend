package com.web.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data @AllArgsConstructor @NoArgsConstructor
public class ScheduleSearchSortParameters {
    private int pgSize = 1;
    private int pgNum = 10;
    private String searchSelect = "";
    private String searchString = "";
    private String dateSelect = "";
    private LocalDate date = LocalDate.now();
    private String startTimeSelect = "";
    private LocalTime startTime = LocalTime.now();
    private String endTimeSelect = "";
    private LocalTime endTime = LocalTime.now();
    private String showSelect = "";
    private String sortDir = "";
    private String sortCol = "";
}
