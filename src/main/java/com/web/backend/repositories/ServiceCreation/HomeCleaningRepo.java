package com.web.backend.repositories.ServiceCreation;


import com.web.backend.model.ServiceCreation.HomeCleaningModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeCleaningRepo extends MongoRepository<HomeCleaningModel, Long> {
}

