package com.halil.bookwebsite.services;

import com.halil.bookwebsite.entities.Category;

import java.util.List;

public interface CategoryService {

    Category getByID(Long id);

    List<Category> getAllCategories();
}
