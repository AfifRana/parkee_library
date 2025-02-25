package com.parkee.library_management.controller;

import com.parkee.library_management.model.entity.Book;
import com.parkee.library_management.model.request.NewBookRq;
import com.parkee.library_management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Afif Rana M
 * on 23/02/2025
 */
@RestController
@RequestMapping("/api/v1")
public class BookController {
    private final BookService bookService;

    public BookController(@Autowired BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBook());
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody NewBookRq newBookRq) {
        return ResponseEntity.ok(bookService.addBook(newBookRq));
    }
}
