package com.web.backend.repositories.crewAssignment;

import com.web.backend.model.crewAssignment.Zone;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ZoneRepository extends MongoRepository<Zone, String> {
    Optional<Zone> getZoneBySign(String sign);
}
