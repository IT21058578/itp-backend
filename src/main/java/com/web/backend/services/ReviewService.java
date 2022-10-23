package com.web.backend.services;

import com.web.backend.exception.AlreadyExistsException;
import com.web.backend.exception.NotFoundException;
import com.web.backend.model.job.Review;
import com.web.backend.repositories.JobRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor @Slf4j
public class ReviewService {
    private final JobRepository repo;

    public Review getReview(String jobId) {
        log.info("Getting details of job with id : {}", jobId);
        Job job = repo.findById(jobId).orElseThrow(NotFoundException::new);
        return job.getReview();
    }

    public void deleteReview(String jobId) {
        log.info("Getting details of job with id : {}", jobId);
        Job job = repo.findById(jobId).orElseThrow(NotFoundException::new);

        log.info("Deleting review...");
        job.setReview(null);

        log.info("Saving job...");
        repo.save(job);
    }

    public void editReview(String jobId, Review review) {
        log.info("Getting details of job with id : {}", jobId);
        Job job = repo.findById(jobId).orElseThrow(NotFoundException::new);

        log.info("Replace updating job's review details...");
        job.setReview(review);

        log.info("Saving job...");
        repo.save(job);
    }

    public void postReview(String jobId, Review review) {
        log.info("Getting details of job with id : {}", jobId);
        Job job = repo.findById(jobId).orElseThrow(NotFoundException::new);
        if (job.getReview() != null) { throw new AlreadyExistsException("Job already has a review");}

        log.info("Creating review...");
        job.setReview(review);

        log.info("Saving job...");
        repo.save(job);
    }
}
