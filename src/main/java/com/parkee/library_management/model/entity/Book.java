package com.parkee.library_management.model.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author Afif Rana M
 * on 23/02/2025
 */
@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(unique = true, nullable = false)
    private String isbn;

    @Column(nullable = false)
    private int stock;
}