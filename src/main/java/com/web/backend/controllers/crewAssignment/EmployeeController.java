package com.web.backend.controllers.crewAssignment;

import com.web.backend.dto.crewAssignment.EmployeeSearchSortParams;
import com.web.backend.model.crewAssignment.Employee;
import com.web.backend.services.crewAssignment.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/employee") @RestController @Slf4j
@AllArgsConstructor @CrossOrigin
public class EmployeeController {
    private final EmployeeService service;

    @PostMapping
    public ResponseEntity<?> postEmployee(@RequestBody Employee employee) {
        log.info("EmployeeController received POST request; {}", employee);
        service.postEmployee(employee);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/toggle", params = {"id", "disable"})
    public ResponseEntity<?> toggleEmployeeDisable(@RequestParam String id, @RequestParam Boolean disable) {
        log.info("EmployeeController received PUT request to toggle disabled status for {} to {}", id, disable);
        service.toggleEmployeeDisable(id, disable);
        return ResponseEntity.ok().build();
    }

    @PutMapping(params = {"id"})
    public ResponseEntity<?> putEmployee(@RequestBody Employee employee, @RequestParam String id) {
        log.info("EmployeeController received PUT request with id {} ; {}", id, employee);
        service.putEmployee(id, employee);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/zoneassign", params={"zoneId", "employeeId"})
    public ResponseEntity<?> assignToZone(@RequestParam String zoneId,
                                          @RequestParam String employeeId,
                                          @RequestParam(required = false, defaultValue = "false") Boolean unassign) {
        log.info("EmployeeController received a PUT request to assign/unassign {} to/from zone {} ", employeeId, zoneId);
        service.assignToZone(zoneId, employeeId, unassign);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/jobassign", params={"id"})
    public ResponseEntity<?> assignToJob(@RequestParam String id) {
        log.info("EmployeeController received a PUT request to assign employees to job {}", id);
        return ResponseEntity.ok(service.assignEmployeesToJob(id));
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<?> getEmployee(@RequestParam String id) {
        log.info("EmployeeController  received GET request with id {}", id);
        return ResponseEntity.ok(service.getEmployee(id));
    }

    @PostMapping(value="/search")
    public ResponseEntity<?> getEmployeeList(@RequestBody EmployeeSearchSortParams searchParams) {
        log.info("EmployeeController received GET request with params {}", searchParams);
        return ResponseEntity.ok(service.getEmployeeList(searchParams));
    }
}
