package com.web.backend.repositories.ServiceCreation;


import com.web.backend.model.ServiceCreation.LaundryServiceModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaundryServiceRepo extends MongoRepository<LaundryServiceModel, Long> {
}


