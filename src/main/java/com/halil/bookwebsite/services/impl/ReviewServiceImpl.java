package com.halil.bookwebsite.services.impl;

import com.halil.bookwebsite.entities.Review;
import com.halil.bookwebsite.exceptions.AlreadyExistsException;
import com.halil.bookwebsite.exceptions.NotFoundException;
import com.halil.bookwebsite.repositories.ReviewRepository;
import com.halil.bookwebsite.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
        if (!reviewRepository.existsById(review.getId())) {
            throw new NotFoundException("Review not found");
        }
        return reviewRepository.save(review);
    }

    @Override
    public void deleteByID(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new NotFoundException("Review not found");
        }
        reviewRepository.deleteById(id);
    }

    @Override
    public boolean checkCurrentUserHasEntity(Long userId, Long entityId) {
        Optional<Review> reviewOptional = reviewRepository.findById(entityId);

        if (reviewOptional.isEmpty()) {
            throw new NotFoundException("Review not found");
        }

        return Objects.equals(reviewOptional.get().getUserId(), userId);
    }
}
