package com.web.backend.services;

import com.web.backend.dto.EmployeeSimple;
import com.web.backend.exception.NotFoundException;
import com.web.backend.model.user.AppUser;
import com.web.backend.model.user.Employee;
import com.web.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor @Service @Slf4j
public class EmployeeService {
    private final MongoTemplate template;
    private final UserRepository repository;

    public List<EmployeeSimple> getEmployeeList() {
        //Prepare preliminary variables.
        log.info("Preparing query variables...");

        //Build query
        log.info("Building query...");
        var query = new Query();
        query.addCriteria(Criteria.where("").is(""));

        //Employees are found
        log.info("Finding employees...");
        var employeeList = template.find(query, Employee.class);

        //Employees are converted to EmployeeSimple and returned.
        log.info("Converting employee to employeeSimple and returning...");
        return employeeList.stream().map(EmployeeSimple::new).toList();
    }

    public AppUser getEmployeeDetails(String id) {
        log.info("Getting details of employee with id {} ...", id);
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void createEmployee(AppUser employee) {
        log.info("Creating new employee with details {} ...", employee);
        repository.save(employee);
    }

    public void deleteEmployee(String id) {
        log.info("Deleting employee with id {} ...", id);
        repository.deleteById(id);
    }

    public void editEmployee(String id, AppUser appUser) {
        log.info("Getting details of employee with id {} ...", id);
        AppUser oldAppUser = repository.findById(id).orElseThrow(NotFoundException::new);
        log.info("Replacing old details with new details...");
        appUser.setId(oldAppUser.getId());
        log.info("Saving new employee details...");
        repository.save(appUser);
    }
}
