package com.web.backend.dto.schedManagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
public class JobSearchSortParameters {
    private int pgNum = 1;
    private int pgSize = 10;
    private String jobId = "";
    private String lengthSelect = "";
    private int length = 0;
    private String dateSelect = "";
    private LocalDate date = LocalDate.now();
    private String crewSelect = "";
    private int crew = 0;
    private String revenueSelect = "";
    private double revenue = 0;
    private String ratingSelect = "";
    private float rating = 0;
    private String sortCol = "";
    private String sortDir = "";
}
