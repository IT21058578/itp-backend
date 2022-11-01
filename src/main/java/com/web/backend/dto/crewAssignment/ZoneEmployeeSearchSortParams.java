package com.web.backend.dto.crewAssignment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class ZoneEmployeeSearchSortParams {
    private String id = "";
    private int pgNum = 1;
    private int pgSize = 10;
    private String sortCol = "";
    private String sortDir = "";
}
