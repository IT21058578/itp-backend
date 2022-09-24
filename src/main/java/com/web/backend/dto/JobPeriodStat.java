package com.web.backend.dto;

import com.web.backend.model.job.Job;
import com.web.backend.model.jobService.Service;
import com.web.backend.utils.heaps.CustomMaxHeap;
import com.web.backend.utils.heaps.CustomMinHeap;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class JobPeriodStat {
    private int jobsWorked;
    private int crewDeployed;
    private long totalHoursWorked;
    private double avgHrsWorked;
    private double totalRating;
    private double avgRating;
    private double totalEarnings;
    private double avgEarnings;
    private List<JobSimple> highEarningJobList;//Field for highest earning jobs
    private List<JobSimple> lowEarningJobList;//Field for lowest earning jobs
    private List<JobSimple> highRatedJobList;//Field for highest rated customer reviews and related job
    private List<JobSimple> lowRatedJobList;//Field for lowest rated customer review and related job
    private List<JobDayStat> jobDayStatList; //Will allow to show a graph for money, hours worked etc.
    private final int idxCnt = 5;

    public JobPeriodStat(int month, int year, int startDay, int endDay, List<Job> jobList) {
        //Best way to implement is to maintain multiple minHeaps and maxHeaps with a max size of idxCnt
        //Manipulate the multiple heaps while going through the list. Once the list has been iterated
        //through we can get the stuff from the list from the recorded index and insert it above.
        var highEarningJobHeap = new CustomMinHeap<JobSimple>(idxCnt);
        var lowEarningJobHeap = new CustomMaxHeap<JobSimple>(idxCnt);
        var highRatedJobHeap = new CustomMinHeap<JobSimple>(idxCnt);
        var lowRatedJobHeap = new CustomMaxHeap<JobSimple>(idxCnt);
        int jobsWorked = 0;
        int crewDeployed = 0;
        long totalHrsWorked = 0;
        double totalRating = 0;
        double totalEarnings = 0;

        for (Job job: jobList) {
            var jobSimple = new JobSimple(job);
            highEarningJobHeap.add(jobSimple.getEarnings(), jobSimple);
            lowEarningJobHeap.add(jobSimple.getEarnings(), jobSimple);
            highRatedJobHeap.add(jobSimple.getRating(), jobSimple);
            lowRatedJobHeap.add(jobSimple.getRating(), jobSimple);

            jobsWorked += 1;
            crewDeployed += jobSimple.getCrewDeployed();
            totalRating += jobSimple.getRating();
            totalEarnings += jobSimple.getEarnings();
            totalHrsWorked += jobSimple.getHoursWorked();
        }

        this.jobsWorked = jobsWorked;
        this.crewDeployed = crewDeployed;
        this.totalRating = totalRating;
        this.avgRating = ((double) totalRating) / jobsWorked;
        this.totalEarnings = totalEarnings;
        this.avgEarnings = ((double) totalEarnings) / jobsWorked;
        this.totalHoursWorked = totalHrsWorked;
        this.avgHrsWorked = ((double) totalHrsWorked) / jobsWorked;

        var jobDayStatList = new ArrayList<JobDayStat>();
        var jobListsPerDay = new ArrayList<List<Job>>(Math.abs(startDay - endDay));
        jobList.forEach(job -> jobListsPerDay.get(job.getCreatedAt().getDayOfMonth() - startDay).add(job));
        jobListsPerDay.forEach(dayJobs -> jobDayStatList.add(new JobDayStat(dayJobs)));

        this.jobDayStatList = jobDayStatList;
        this.highEarningJobList = highEarningJobHeap.toList();
        this.lowEarningJobList = lowEarningJobHeap.toList();
        this.highRatedJobList = highRatedJobHeap.toList();
        this.lowRatedJobList = lowRatedJobHeap.toList();
    }
}
