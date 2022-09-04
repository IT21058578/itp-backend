package com.web.backend.dto;

import com.web.backend.model.job.Job;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class JobSimple {
    private int hoursWorked;
    private int employeeNum;
    private float rating;
    private String id;
    private String review;
    private String earnings;
    private List<String> employeeIds;

    public JobSimple (Job job) {

    }
}
