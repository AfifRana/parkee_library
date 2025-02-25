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

    public List<Book> getBooks(String title) {
        if (title != null && !title.isBlank()) {
            return getBookByTitleContaining(title);
        } else {
            return bookRepository.findAll();
        }
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElse(null);
    }

    public List<Book> getBookByTitleContaining(String title) {
        return bookRepository.findByTitleContaining(title);
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

    public Book updateBookStock(Long id, int stock) {
        Book book = bookRepository.findById(id).orElse(null);

        if (book == null) {
            return null;
        }

        book.setStock(stock);
        return bookRepository.save(book);
    }
}