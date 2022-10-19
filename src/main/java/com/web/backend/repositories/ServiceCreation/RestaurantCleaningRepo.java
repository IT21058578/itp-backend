package com.web.backend.repositories.ServiceCreation;

import com.web.backend.model.ServiceCreation.RestaurantCleaningModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantCleaningRepo extends MongoRepository<RestaurantCleaningModel, Long> {
}


