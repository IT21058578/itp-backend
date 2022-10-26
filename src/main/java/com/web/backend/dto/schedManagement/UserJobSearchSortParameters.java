package com.web.backend.dto.schedManagement;

import lombok.Data;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

@Data
public class UserJobSearchSortParameters {
    private String email;
    private String type;
    private Integer pgNum = 1;
    private Integer pgSize = 10;
}
