package com.halil.bookwebsite.controllers;

import com.halil.bookwebsite.entities.Review;
import com.halil.bookwebsite.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PreAuthorize("@authComponent.hasPermission('manageReviewAdmin') or @authComponent.hasPermission('manageReviewUser', reviewService, #id)")
    @PutMapping("/private/review/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review review) {
        review.setId(id);
        return new ResponseEntity<>(reviewService.update(review), HttpStatus.OK);
    }

    @PreAuthorize("@authComponent.hasPermission('manageReviewAdmin') or @authComponent.hasPermission('manageReviewUser', reviewService, #id)")
    @DeleteMapping("/private/review/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable Long id) {
        reviewService.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("@authComponent.hasPermission('manageReviewUser') or @authComponent.hasPermission('manageReviewAdmin')")
    @PostMapping("/public/review")
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        return new ResponseEntity<>(reviewService.create(review), HttpStatus.CREATED);
    }
}
