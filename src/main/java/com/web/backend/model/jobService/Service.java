package com.web.backend.model.jobService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Service {
    protected String id;
    protected String name;
    protected String imgUrl;
    protected String desc;
    protected double basicPrice;
}
