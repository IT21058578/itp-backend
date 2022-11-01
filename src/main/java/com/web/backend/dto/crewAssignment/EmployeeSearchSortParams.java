package com.web.backend.dto.crewAssignment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class EmployeeSearchSortParams {
    private int pgNum = 1;
    private int pgSize = 10;
    private String sortCol = "";
    private String sortDir = "";
    private String zoneSelect = ""; //Find by zone id.
}
