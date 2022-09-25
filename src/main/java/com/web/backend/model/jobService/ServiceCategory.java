package com.web.backend.model.jobService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "serviceCategory")
@Data @AllArgsConstructor @NoArgsConstructor
public class ServiceCategory {
    private String id;
    private String name;
    private String imgUrl;
    private String desc;
    private List<Service> services;

    public ServiceCategory(String name, String imgUrl, String desc, List<Service> services) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.desc = desc;
        this.services = services;
    }
}
