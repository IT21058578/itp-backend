package com.web.backend.model.crewAssignment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString @AllArgsConstructor
public enum EmployeeType {
    CLEANER("Cleaner"),
    DRIVER("Driver"),
    ;

    private final String text;
}
