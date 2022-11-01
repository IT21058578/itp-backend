package com.web.backend.controllers;

import com.web.backend.dto.EmployeeSearchSortParams;
import com.web.backend.model.crewAssignment.Employee;
import com.web.backend.services.EmployeeService;
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
        log.info("EmployeeController received CREATE request; {}", employee);
        service.postEmployee(employee);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<?> deleteEmployee(@RequestParam String id) {
        log.info("EmployeeController received DELETE request with id {}", id);
        service.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(params = {"id"})
    public ResponseEntity<?> putEmployee(@RequestBody Employee employee, @RequestParam String id) {
        log.info("EmployeeController received PUT request with id {} ; {}", id, employee);
        service.putEmployee(id, employee);
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<?> getEmployee(@RequestParam String id) {
        log.info("EmployeeController  received GET request with id {}", id);
        return ResponseEntity.ok(service.getEmployee(id));
    }

    @PostMapping(value = "/search")
    public ResponseEntity<?> getEmployeeList(EmployeeSearchSortParams searchParams) {
        log.info("EmployeeController received POST request for employees with paging");
        return ResponseEntity.ok(service.getEmployeeList(searchParams));
    }
}
