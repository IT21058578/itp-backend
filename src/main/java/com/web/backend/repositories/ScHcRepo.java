package com.web.backend.repositories;

import com.web.backend.model.HomeCleaning;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScHcRepo extends MongoRepository<HomeCleaning, Long>{
}
