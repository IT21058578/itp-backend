package com.web.backend.repositories.ServiceCreation;

import com.web.backend.model.ServiceCreation.SportCentersCleaningModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportCenterCleaningRepo extends MongoRepository<SportCentersCleaningModel, Long> {
}


