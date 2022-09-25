package com.web.backend.model.jobService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data @NoArgsConstructor @AllArgsConstructor
public class Service {
    @Id
    protected String id;
    protected String name;
    protected String imgUrl;
    protected String desc;
    protected double basicPrice;

    public Service(String name, String imgUrl, String desc, double basicPrice) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.desc = desc;
        this.basicPrice = basicPrice;
    }
}
