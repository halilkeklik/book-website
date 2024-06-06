package com.halil.bookwebsite.services.impl;

import com.halil.bookwebsite.entities.Book;
import com.halil.bookwebsite.exceptions.AlreadyExistsException;
import com.halil.bookwebsite.exceptions.NotFoundException;
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
            throw new NotFoundException("Book not found");
        return bookOptional.get();
    }

    @Override
    public Book update(Book book) {
        if (bookRepository.existsById(book.getId()))
            throw new NotFoundException("Book not found");
        return bookRepository.save(book);
    }

    @Override
    public void deleteByID(Long id) {
        if (bookRepository.existsById(id))
            throw new NotFoundException("Book not found");
        bookRepository.deleteById(id);
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }
}
