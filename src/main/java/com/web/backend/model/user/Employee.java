package com.web.backend.model.user;

import lombok.Data;

@Data
public class Employee extends UserKind {
    private String jobTitle;
    private String zone;
}
