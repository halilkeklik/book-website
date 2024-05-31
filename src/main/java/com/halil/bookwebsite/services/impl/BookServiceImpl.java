package com.halil.bookwebsite.services.impl;

import com.halil.bookwebsite.entities.Book;
import com.halil.bookwebsite.repositories.BookRepository;
import com.halil.bookwebsite.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book getByID(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty())
            throw new RuntimeException();
        return bookOptional.get();
    }

    @Override
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteByID(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book createBlog(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }
}
