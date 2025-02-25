package com.parkee.library_management.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Afif Rana M
 * on 23/02/2025
 */
@Data
@Entity
@Table(name = "borrowings")
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "borrower_id", nullable = false)
    private Borrower borrower;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "borrow_date", nullable = false)
    private LocalDate borrowDate;

    @Column(name = "return_deadline", nullable = false)
    private LocalDate returnDeadline;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    // Constructors, Getters, and Setters

    public Borrowing() {
    }

    public Borrowing(Borrower borrower, Book book, LocalDate borrowDate, LocalDate returnDeadline, Status status) {
        this.borrower = borrower;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDeadline = returnDeadline;
        this.status = status;
    }

    // Enum for Status
    public enum Status {
        BORROWED,
        RETURNED,
        LATE
    }
}
