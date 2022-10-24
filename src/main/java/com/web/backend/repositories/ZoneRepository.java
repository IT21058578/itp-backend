package com.university.university.university.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.university.university.university.Collections.Zones;

@Repository
public interface ZoneRepository extends MongoRepository<Zones,String>{

    public boolean existsById(String id);

    public boolean existsByArea(String area);

}