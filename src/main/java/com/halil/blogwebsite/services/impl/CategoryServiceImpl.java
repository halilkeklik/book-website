package com.halil.blogwebsite.services.impl;

import com.halil.blogwebsite.entities.Author;
import com.halil.blogwebsite.entities.Category;
import com.halil.blogwebsite.repositories.CategoryRepository;
import com.halil.blogwebsite.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getByID(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty())
            throw new RuntimeException();
        return categoryOptional.get();
    }

    @Override
    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }
}
