package com.web.backend.repositories.addressManagement;

import com.web.backend.model.addressManagement.addressManagement;
import com.web.backend.model.booking.booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface addressRepository extends MongoRepository<addressManagement, String> {
}
