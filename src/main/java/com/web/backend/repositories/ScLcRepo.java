package com.web.backend.repositories;

import com.web.backend.model.LaundryServices;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScLcRepo extends MongoRepository<LaundryServices, Long> {
}
