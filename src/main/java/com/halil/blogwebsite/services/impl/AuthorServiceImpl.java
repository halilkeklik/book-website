package com.halil.blogwebsite.services.impl;

import com.halil.blogwebsite.entities.Author;
import com.halil.blogwebsite.repositories.AuthorRepository;
import com.halil.blogwebsite.services.AuthorService;
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
