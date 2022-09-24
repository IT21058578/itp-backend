package com.web.backend.dto;

import com.web.backend.model.user.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class EmployeeSimple {
    private String id;
    private String firstName;
    private String lastName;
    private String jobTitle;

    public EmployeeSimple(Employee e) {
    }
}
