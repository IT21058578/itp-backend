package com.web.backend.repositories.addressManagement;

import com.web.backend.model.addressManagement.addressManagement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface addressRepository extends MongoRepository<addressManagement, String> {
}
