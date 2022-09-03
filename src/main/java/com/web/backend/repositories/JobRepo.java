package com.web.backend.repositories;

import com.web.backend.model.job.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepo extends MongoRepository<Job, String> {
}
