package com.halil.blogwebsite.services;

import com.halil.blogwebsite.entities.Category;

import java.util.List;

public interface CategoryService {

    Category getByID(Long id);

    List<Category> getAllCategories();
}
