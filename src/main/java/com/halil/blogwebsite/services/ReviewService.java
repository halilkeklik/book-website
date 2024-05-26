package com.halil.blogwebsite.services;

import com.halil.blogwebsite.entities.Review;

public interface ReviewService {

    Review create(Review review);

    Review update(Review review);

    void deleteByID(Long id);
}
