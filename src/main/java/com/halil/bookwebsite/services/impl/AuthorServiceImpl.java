package com.halil.bookwebsite.services.impl;

import com.halil.bookwebsite.entities.Author;
import com.halil.bookwebsite.repositories.AuthorRepository;
import com.halil.bookwebsite.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author getByID(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isEmpty())
            throw new RuntimeException();
        return authorOptional.get();
    }

    @Override
    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }

}
