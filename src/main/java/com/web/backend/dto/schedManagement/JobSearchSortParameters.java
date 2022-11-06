package com.web.backend.dto.schedManagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class JobSearchSortParameters {
    private String sortDir = "";
    private String sortCol = "";
    private int pgSize = 1;
    private int pgNum = 10;
    private String zoneId = "";
    private String employeeId = "";
    private String clientId = "";
    private String status = "";
    private boolean paging = true;
}
