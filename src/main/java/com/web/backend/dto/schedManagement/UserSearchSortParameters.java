package com.web.backend.dto.schedManagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor @NoArgsConstructor @Data
public class UserSearchSortParameters {
    private int pgSize = 1;
    private int pgNum = 10;
    private String searchSelect = "";
    private String searchString = "";
    private String showSelect = "";
    private String sortDir = "";
    private String sortCol = "";
}
