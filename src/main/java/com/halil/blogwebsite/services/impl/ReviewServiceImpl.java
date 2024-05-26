package com.halil.blogwebsite.services.impl;

import com.halil.blogwebsite.entities.Review;
import com.halil.blogwebsite.repositories.ReviewRepository;
import com.halil.blogwebsite.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review update(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteByID(Long id) {
        reviewRepository.deleteById(id);
    }
}
