package com.web.backend.repositories.ServiceCreation;


import com.web.backend.model.ServiceCreation.OfficeCleaningModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeCleaningRepo extends MongoRepository<OfficeCleaningModel, Long> {
}


