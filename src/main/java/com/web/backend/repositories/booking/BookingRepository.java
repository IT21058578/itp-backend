package com.web.backend.repositories.booking;

import com.web.backend.model.booking.booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<booking, String> {
}
