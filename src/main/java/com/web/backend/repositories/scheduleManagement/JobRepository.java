package com.web.backend.repositories.scheduleManagement;

import com.web.backend.model.job.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends MongoRepository<Job, String> {

}
