package com.web.backend.services.crewAssignment;

import com.web.backend.dto.crewAssignment.EmployeeSearchSortParams;
import com.web.backend.exception.AlreadyExistsException;
import com.web.backend.exception.NotFoundException;
import com.web.backend.model.crewAssignment.Employee;
import com.web.backend.model.crewAssignment.EmployeeType;
import com.web.backend.model.crewAssignment.Zone;
import com.web.backend.model.job.Job;
import com.web.backend.repositories.crewAssignment.EmployeeRepository;
import com.web.backend.services.scheduleManagement.JobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.stream.Collectors;

@Service @Slf4j @AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepo;
    private final JobService jobService;
    private final ZoneService zoneService;
    private final MongoTemplate template;

    public Job assignEmployeesToJob(String id) {
        Job job = jobService.getJobPageDetails(id);
        return assignEmployeesToJob(job);
    }

    public Job assignEmployeesToJob(Job job) {
        //TODO: Remake this function
        log.info("Assigning crew to job with id {}", job.getId());

        if ( job.getZoneId() == null ) {
            throw new IllegalStateException("Cannot assign crew to a job with no specified zone.");
        } else if ( job.getStartTime() == null ) {
            throw new IllegalStateException("Cannot assign crew to a job with no start time.");
        } else if ( job.getEndTime() == null ) {
            throw new IllegalStateException("Cannot assign crew to a job with no end time.");
        }

        log.info("Creating aggregation pipeline...");
        var aggregationPipeline = new ArrayList<AggregationOperation>();

        log.info("Modifying pipeline according to job parameters...");
        aggregationPipeline.add(Aggregation.match(Criteria.where("isDisabled").is(false))); //Get only enabled employees
        aggregationPipeline.add(Aggregation.match(Criteria.where("zoneAssignmentsList.id").regex(job.getZoneId())));

        log.info("Finalizing aggregation pipeline...");
        var aggregation = Aggregation.newAggregation(aggregationPipeline);

        log.info("Creating viable employee list...");
        var viableEmployeeList = template.aggregate(aggregation, "Employee", Employee.class).getMappedResults();
        if (viableEmployeeList.isEmpty()) {
            throw new NotFoundException("Unable to find employees according to job specification");
        }

        log.info("Selecting from viable employee list...");
        var selectedEmployeeList = new ArrayList<Employee>();
        selectedEmployeeList.add(viableEmployeeList.stream()
                .filter(emp -> emp.getJobTitle().equals(EmployeeType.DRIVER)).findFirst()
                .orElseThrow(() -> new NotFoundException("Unable to find drivers according to job specification")));
        selectedEmployeeList.add(viableEmployeeList.stream()
                .filter(emp -> emp.getJobTitle().equals(EmployeeType.CLEANER)).findFirst()
                .orElseThrow(() -> new NotFoundException("Unable to find cleaners according to job specification")));

        //TODO: Add jobAssignments here.

        log.info("Inserting crew list to job...");
        job.setCrewList(selectedEmployeeList.stream()
                .map(Job.JobCrewMemberSimple::new).collect(Collectors.toList()));

        return job;
    }

    public void assignToZone(String zoneId, String employeeId, Boolean unassign) {
        Employee employee = getEmployee(employeeId);
        var zoneAssignmentList = employee.getZoneAssignmentsList();

        log.info("Checking if assignment exists");
        if (!unassign) {
            if (zoneAssignmentList.stream().map(Employee.ZoneAssignment::getId)
                    .anyMatch(id -> id.equals(zoneId))) {
                throw new AlreadyExistsException("Employee is already assigned to this zone");
            }

            Zone zone = zoneService.getZone(zoneId);
            var newAssignment = new Employee.ZoneAssignment(
                    zone.getId(), zone.getSign(), LocalDate.now(), LocalDate.now());
            zoneAssignmentList.add(newAssignment);
        } else {
            if (zoneAssignmentList.stream().map(Employee.ZoneAssignment::getId)
                    .noneMatch(id -> id.equals(zoneId))) {
                throw new AlreadyExistsException("Employee is not assigned to this zone");
            }

            zoneAssignmentList.removeIf(zone -> zone.getId().equals(zoneId));
        }
        

        log.info("Updating and saving employee...");
        employee.setZoneAssignmentsList(zoneAssignmentList);
        employeeRepo.save(employee);
    }

    public void postEmployee(Employee employee) {
        log.info("Creating new employee : {}", employee);
        employee.setJoinedOn(LocalDate.now());
        employee.setZoneAssignmentsList(Collections.emptyList());
        employee.setDisabled(false);
        employeeRepo.save(employee);
    }

    public void toggleEmployeeDisable(String id, boolean disable) {
        log.info("Disabling employee with id {}", id);
        Employee employee = getEmployee(id);
        employee.setDisabled(disable);
        employeeRepo.save(employee);
    }

    public void putEmployee(String id, Employee employee) {
        Employee dbEmployee = getEmployee(id);

        log.info("Updating employee details...");
        dbEmployee.setFirstName(employee.getFirstName());
        dbEmployee.setLastName(employee.getLastName());
        dbEmployee.setEmail(employee.getEmail());
        dbEmployee.setMobile(employee.getMobile());
        dbEmployee.setJobTitle(employee.getJobTitle());

        log.info("Saving updated employee...");
        employeeRepo.save(dbEmployee);

    }

    public Employee getEmployee(String id) {
        log.info("Getting employee with id {}", id);
        return employeeRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    public Page<Employee> getEmployeeList(EmployeeSearchSortParams searchParams) {
        log.info("Getting employee page with params : {}", searchParams);

        log.info("Building aggregation pipeline...");
        var pageRequest = PageRequest.of(searchParams.getPgNum(), searchParams.getPgNum());
        var aggregationPipeline = new ArrayList<AggregationOperation>();
        aggregationPipeline.add(Aggregation.match(Criteria.where(""))); //So that error is not thrown later

        if (!searchParams.getJobId().isBlank()) {
            log.info("jobId is present");
            Job job = jobService.getJobPageDetails(searchParams.getJobId());
            var employeeIds = job.getCrewList().stream().map(e -> new ObjectId(e.getId())).toList();
            aggregationPipeline.add(Aggregation.match(Criteria.where("_id").in(employeeIds)));
        }

        log.info("Modifying pipeline according to searching params");
        if (!searchParams.getZoneId().isBlank()) {
            log.info("zoneId is present");
            aggregationPipeline.add(Aggregation.match(Criteria.where("zoneAssignmentsList")
                    .elemMatch(Criteria.where("_id").is(new ObjectId(searchParams.getZoneId())))));
        }

        if (!searchParams.getEmployeeId().isBlank()) {
            log.info("employeeId is present");
            aggregationPipeline.add(Aggregation.match(Criteria.where("_id").is(new ObjectId(searchParams.getEmployeeId()))));
        }

        if (!searchParams.getStatus().isBlank()) {
            log.info("employeeId is present");
            String status = searchParams.getStatus().toLowerCase(Locale.ROOT);
            if (status.equals("true") || status.equals("false")) {
                aggregationPipeline.add(Aggregation.match(Criteria.where("disabled").regex(status)));
            }
        }

        if (!searchParams.getSortCol().isBlank()) {
            log.info("Modifying pipeline according to sort params...");
            switch (searchParams.getSortDir()) {
                case "asc" -> aggregationPipeline.add(Aggregation.sort(Sort.Direction.ASC, searchParams.getSortCol()));
                case "desc" -> aggregationPipeline.add(Aggregation.sort(Sort.Direction.DESC, searchParams.getSortCol()));
            }
        }

        log.info("Getting total number of elements...");
        var aggregation = Aggregation.newAggregation(aggregationPipeline);
        long totalCount = template.aggregate(aggregation, "employee", Employee.class).getMappedResults().size();

        if (searchParams.isPaging()) {
            log.info("Limiting query according to page params...");
            aggregationPipeline.add(Aggregation.skip((searchParams.getPgNum() - 1) * searchParams.getPgSize()));
            aggregationPipeline.add(Aggregation.limit(searchParams.getPgSize()));
        }

        log.info("Getting elements...");
        aggregation = Aggregation.newAggregation(aggregationPipeline);
        var employeeList = template.aggregate(aggregation, "employee" ,Employee.class).getMappedResults();
        var employeePage = new PageImpl<>(employeeList, pageRequest, totalCount);

        log.info("Returning page...");
        return employeePage;
    }
}
