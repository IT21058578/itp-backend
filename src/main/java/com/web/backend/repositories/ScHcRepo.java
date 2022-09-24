package com.atles.test.repositories;

import com.atles.test.model.HomeCleaning;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScHcRepo extends MongoRepository<HomeCleaning, Long>{
}
