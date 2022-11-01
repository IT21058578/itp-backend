package com.web.backend.repositories;

import com.web.backend.model.crewAssignment.Zone;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ZoneRepository extends MongoRepository<Zone, String> {
}
