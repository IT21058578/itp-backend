package com.web.backend.repositories.crewAssignment;

import com.web.backend.model.crewAssignment.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
