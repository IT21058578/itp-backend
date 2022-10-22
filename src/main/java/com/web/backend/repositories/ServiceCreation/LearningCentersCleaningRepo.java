package com.web.backend.repositories.ServiceCreation;


import com.web.backend.model.ServiceCreation.LearningCentersCleaningModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningCentersCleaningRepo extends MongoRepository<LearningCentersCleaningModel, Long> {
}

