package com.web.backend.dto.crewAssignment;

import com.web.backend.model.crewAssignment.Employee;
import com.web.backend.model.crewAssignment.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data
public class EmployeeExtendedData {
    private String id;
    private String firstName;
    private String lastName;
    private EmployeeType jobTitle;
    private LocalDate joinedOn;
    private LocalDate lastJobOn;
    private List<Employee.ZoneAssignment> zoneAssignmentsList;
    private List<Employee.JobAssignment> jobAssignmentsList;
    private boolean isDisabled;

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class ZoneAssignment {
        private String id; //Zone Id
        private String sign; //Zone String
        private LocalDate assignedOn;
        private LocalDate lastJobOn;
    }

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class JobAssignment {
        private String id; //Job Id
        private LocalDateTime startTime;
        private LocalDateTime endTime;
    }
}
