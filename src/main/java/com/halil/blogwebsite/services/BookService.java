package com.halil.blogwebsite.services;

import com.halil.blogwebsite.entities.Book;

public interface BookService {
    Book getByID(Long id);

    Book update(Book book);

    void deleteByID(Long id);

    Book createBlog(Book book);
}
