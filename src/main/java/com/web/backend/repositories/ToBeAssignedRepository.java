package com.university.university.university.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.university.university.university.Collections.ToBeAssigned;

@Repository
public interface ToBeAssignedRepository extends MongoRepository<ToBeAssigned,String>{
    public boolean existsById(String id);
    
}
