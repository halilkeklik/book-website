package com.halil.bookwebsite.services;

import com.halil.bookwebsite.entities.Review;

public interface ReviewService {

    Review create(Review review);

    Review update(Review review);

    void deleteByID(Long id);
}
