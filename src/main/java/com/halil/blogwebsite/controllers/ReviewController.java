package com.halil.blogwebsite.controllers;

import com.halil.blogwebsite.entities.Review;
import com.halil.blogwebsite.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PutMapping("/review/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review review) {
        review.setId(id);
        return new ResponseEntity<>(reviewService.update(review), HttpStatus.OK);
    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable Long id) {
        reviewService.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/review")
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        return new ResponseEntity<>(reviewService.create(review), HttpStatus.CREATED);
    }
}
