package com.web.backend.repositories;


import com.web.backend.model.SC;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScRepository extends MongoRepository<SC, Long> {

}

