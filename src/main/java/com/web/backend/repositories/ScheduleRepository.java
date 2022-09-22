package com.web.backend.repositories;

import com.web.backend.model.job.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScheduleRepository extends MongoRepository<Schedule, String>{
}
