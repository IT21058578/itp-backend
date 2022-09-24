package com.atles.test.repositories;

import com.atles.test.model.LaundryServices;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScLcRepo extends MongoRepository<LaundryServices, Long> {
}
