package com.web.backend.model.user;

import lombok.Data;

public class Employee extends UserKind {
    private String jobTitle;
    private String zone;

    public Employee(UserType type) {
        super(type);
    }
}
