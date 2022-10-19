package com.web.backend.repositories.ServiceCreation;

import com.web.backend.model.ServiceCreation.ServiceRequestModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRequestRepo extends MongoRepository<ServiceRequestModel, Long> {
}
