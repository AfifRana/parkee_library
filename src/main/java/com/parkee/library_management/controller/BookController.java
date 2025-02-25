package com.parkee.library_management.controller;

import com.parkee.library_management.model.entity.Book;
import com.parkee.library_management.model.request.NewBookRq;
import com.parkee.library_management.model.response.GeneralRs;
import com.parkee.library_management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<GeneralRs> getBooks(@RequestParam(required = false) String bookTitle) {
        GeneralRs generalRs = new GeneralRs();
        try {
            List<Book> books = bookService.getBooks(bookTitle);

            generalRs.setStatusCode(HttpStatus.OK.value());
            generalRs.setMessage("Successfully get the books");
            generalRs.setData(books);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        } catch (Exception e) {
            generalRs.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            generalRs.setMessage(e.getMessage());
            generalRs.setData(null);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        }
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<GeneralRs> getBookById(@PathVariable Long bookId) {
        GeneralRs generalRs = new GeneralRs();
        try {
            Book book = bookService.getBookById(bookId);

            generalRs.setStatusCode(HttpStatus.OK.value());
            generalRs.setMessage("Successfully get the book by ID : " + bookId);
            generalRs.setData(book);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        } catch (Exception e) {
            generalRs.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            generalRs.setMessage(e.getMessage());
            generalRs.setData(null);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        }
    }

    @GetMapping("/books/isbn/{isbn}")
    public ResponseEntity<GeneralRs> getBookById(@PathVariable String isbn) {
        GeneralRs generalRs = new GeneralRs();
        try {
            Book book = bookService.getBookByIsbn(isbn);

            generalRs.setStatusCode(HttpStatus.OK.value());
            generalRs.setMessage("Successfully get the book by ISBN : " + isbn);
            generalRs.setData(book);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        } catch (Exception e) {
            generalRs.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            generalRs.setMessage(e.getMessage());
            generalRs.setData(null);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        }
    }

    @PostMapping("/books")
    public ResponseEntity<GeneralRs> addBook(@RequestBody NewBookRq newBookRq) {
        GeneralRs generalRs = new GeneralRs();
        try {
            Book book = bookService.addBook(newBookRq);

            generalRs.setStatusCode(HttpStatus.CREATED.value());
            generalRs.setMessage("Book with ID " + book.getId() + " has been added");
            generalRs.setData(book);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        } catch (Exception e) {
            generalRs.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            generalRs.setMessage(e.getMessage());
            generalRs.setData(null);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        }
    }

    @PutMapping("/books")
    public ResponseEntity<GeneralRs> updateBook(@RequestBody Book bookRq) {
        GeneralRs generalRs = new GeneralRs();
        try {
            Book book = bookService.updateBook(bookRq);

            generalRs.setStatusCode(HttpStatus.CREATED.value());
            generalRs.setMessage("Book with ID " + book.getId() + " has been updated");
            generalRs.setData(book);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        } catch (Exception e) {
            generalRs.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            generalRs.setMessage(e.getMessage());
            generalRs.setData(null);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        }
    }

    @PutMapping("/books/stock/{bookId}")
    public ResponseEntity<GeneralRs> updateBookStock(@PathVariable Long bookId, @RequestParam Integer stock) {
        GeneralRs generalRs = new GeneralRs();
        try {
            Book book = bookService.updateBookStock(bookId, stock);

            generalRs.setStatusCode(HttpStatus.CREATED.value());
            generalRs.setMessage("Stock of book with ID " + book.getId() + " has been updated");
            generalRs.setData(book);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        } catch (Exception e) {
            generalRs.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            generalRs.setMessage(e.getMessage());
            generalRs.setData(null);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        }
    }
}
