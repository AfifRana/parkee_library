package com.parkee.library_management.service;

import com.parkee.library_management.model.entity.Book;
import com.parkee.library_management.model.request.NewBookRq;
import com.parkee.library_management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Afif Rana M
 * on 23/02/2025
 */
@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(@Autowired BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElse(null);
    }

    public Book getBookByTitle(String title) {
        return bookRepository.findByTitle(title).orElse(null);
    }

    public Book addBook(NewBookRq newBookRq) {
        Book book = new Book();
        book.setTitle(newBookRq.getTitle());
        book.setIsbn(newBookRq.getIsbn());
        book.setStock(newBookRq.getStock());
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }
}