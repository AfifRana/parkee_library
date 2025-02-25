package com.parkee.library_management.model.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author Afif Rana M
 * on 23/02/2025
 */
@Data
@Entity
@Table(name = "borrowers")
public class Borrower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ktp_number", unique = true, nullable = false)
    private String ktpNumber;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;
}
