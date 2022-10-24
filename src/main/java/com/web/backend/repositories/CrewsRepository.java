package com.university.university.university.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.university.university.university.Collections.Crews;

@Repository
public interface CrewsRepository extends MongoRepository<Crews,String>{

    public boolean existsByEmployeeId(String employeeId);
}