package com.web.backend.repositories.ServiceCreation;


import com.web.backend.model.ServiceCreation.ApartmentCleaningModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentCleaningRepo extends MongoRepository<ApartmentCleaningModel, Long> {
}
