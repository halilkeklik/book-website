package com.halil.blogwebsite.services;

import com.halil.blogwebsite.entities.Author;

import java.util.List;

public interface AuthorService {

    Author getByID(Long id);

    List<Author> getAllAuthors();

}
