package com.university.university.university.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.university.university.university.Collections.Assigned;

@Repository
public interface AssignedRepository extends MongoRepository<Assigned,String>{
    public boolean existsById(String id);
    
}
