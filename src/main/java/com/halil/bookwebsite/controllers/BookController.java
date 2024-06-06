package com.halil.bookwebsite.controllers;

import com.halil.bookwebsite.entities.Book;
import com.halil.bookwebsite.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/public/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getByID(id), HttpStatus.OK);
    }

    @GetMapping("/public/book")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @PreAuthorize("@authComponent.hasPermission('manageBookAdmin')")
    @PostMapping("/private/book/{id}")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @PreAuthorize("@authComponent.hasPermission('manageBookAdmin')")
    @PutMapping("/private/book/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        return new ResponseEntity<>(bookService.update(book), HttpStatus.OK);
    }

    @PreAuthorize("@authComponent.hasPermission('manageBookAdmin')")
    @DeleteMapping("/private/book/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id) {
        bookService.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
