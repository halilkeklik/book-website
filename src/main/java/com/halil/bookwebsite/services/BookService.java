package com.halil.bookwebsite.services;

import com.halil.bookwebsite.entities.Book;

import java.util.List;

public interface BookService {
    Book getByID(Long id);

    Book update(Book book);

    void deleteByID(Long id);

    Book createBook(Book book);

    List<Book> getAllBooks();
}
