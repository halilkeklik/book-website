package com.halil.bookwebsite.services;

import com.halil.bookwebsite.entities.Author;

import java.util.List;

public interface AuthorService {

    Author getByID(Long id);

    List<Author> getAllAuthors();

}
