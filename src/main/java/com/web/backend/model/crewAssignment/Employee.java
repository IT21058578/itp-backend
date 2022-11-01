package com.web.backend.model.crewAssignment;

import com.web.backend.model.job.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private EmployeeType jobTitle;
    private LocalDate joinedOn;
    private LocalDate lastJobOn;
    private float averageRating = 0;
    private float jobCount = 0;
    private List<ZoneAssignment> zoneAssignmentsList;
    private List<JobAssignment> jobAssignmentsList;
    private boolean isDisabled;

    @Data @AllArgsConstructor
    public static class ZoneAssignment {
        private String id; //Zone Id
        private String sign; //Zone String
        private LocalDate assignedOn;
        private LocalDate lastJobOn;
    }

    @Data @AllArgsConstructor
    public static class JobAssignment {
        private String id; //Job Id
        private LocalDateTime startTime;
        private LocalDateTime endTime;
    }
}
