package com.web.backend.dto;

import com.web.backend.model.job.Job;
import com.web.backend.model.jobService.Service;

import java.util.List;

public class JobPeriodStat {
    private int jobsWorked;
    private int crewDeployed;
    private long hoursWorked;
    private float avgRating;
    private double earnings;
    private List<Service> mostDemandService; //Services with the most Demand.
    private List<Service> leastDemandService; //Services with the least Demand.
    private List<JobSimple> highEarningJobList;//Field for highest earning jobs
    private List<JobSimple> lowEarningJobList;//Field for lowest earning jobs
    private List<JobSimple> highRatedJobList;//Field for highest rated customer reviews and related job
    private List<JobSimple> lowRatedJobList;//Field for lowest rated customer review and related job
    private List<JobDayStat> jobDayStatList; //Will allow to show a graph for money, hours worked etc.
    private List<EmployeeSimple> highRatedEmployeeList;//Field for highest rated crew
    private List<EmployeeSimple> lowRatedEmployeeList;//Field for lowest rate crew
    private List<EmployeeSimple> highHrsEmployeeList;//Field for busiest crew
    private List<EmployeeSimple> lowHrsEmployeeList;//Field for most inactive crew
    private final int idxCnt = 5;

    public JobPeriodStat(List<Job> jobList) {
        //Best way to implement is to maintain multiple minHeaps with a max size of type int
        //and manipulate the multiple heaps while going through the list. Once the list has been iterated
        //through we can get the stuff from the list from the recorded index and insert it above.
    }
}
