package com.halil.bookwebsite.services.impl;

import com.halil.bookwebsite.entities.Review;
import com.halil.bookwebsite.repositories.ReviewRepository;
import com.halil.bookwebsite.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
