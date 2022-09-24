package com.atles.test.repositories;


import com.atles.test.model.SC;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScRepository extends MongoRepository<SC, Long> {

}

