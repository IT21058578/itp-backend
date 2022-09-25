package com.web.backend.controllers;

import com.web.backend.dto.EmployeeSimple;
import com.web.backend.model.user.AppUser;
import com.web.backend.services.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/employee")
@RestController @Slf4j
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping
    public ResponseEntity<List<EmployeeSimple>> getEmployeeList() {
        log.info("EmployeeController received GET request with no params or body");
        return ResponseEntity.ok(service.getEmployeeList());
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<AppUser> getEmployeeDetails(@RequestParam String id) {
        log.info("EmployeeController received GET request with params {}", id);
        return ResponseEntity.ok(service.getEmployeeDetails(id));
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody AppUser employee) {
        log.info("EmployeeController received POST request with body {}", employee);
        service.createEmployee(employee);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<?> deleteEmployee(@RequestParam String id) {
        log.info("EmployeeController received DELETE request with params {}", id);
        service.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> editEmployee(@RequestParam String id, @RequestBody AppUser employee) {
        log.info("EmployeeController received PUT request with params {} and body {}", id, employee);
        service.editEmployee(id, employee);
        return ResponseEntity.ok().build();
    }
}
