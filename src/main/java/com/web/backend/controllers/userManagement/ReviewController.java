package com.web.backend.controllers.userManagement;

import com.web.backend.model.job.Review;
import com.web.backend.services.userManagement.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/review") @RestController @Slf4j
@AllArgsConstructor @CrossOrigin
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<?> getReview(@RequestParam String jobId) {
        log.info("ReviewController received GET Request with jobId : {}", jobId);
        return ResponseEntity.ok(reviewService.getReview(jobId));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteReview(@RequestParam String jobId) {
        log.info("ReviewController received DELETE Request with jobId : {}", jobId);
        reviewService.deleteReview(jobId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> editReview(@RequestParam String jobId, @RequestBody Review review) {
        log.info("ReviewController received PUT Request with jobId : {} , and Review : {}", jobId, review);
        reviewService.editReview(jobId, review);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> postReview(@RequestParam String jobId, @RequestBody Review review) {
        log.info("ReviewController received POST Request with jobId : {} , and Review : {}", jobId, review);
        reviewService.postReview(jobId, review);
        return ResponseEntity.ok().build();
    }
}
