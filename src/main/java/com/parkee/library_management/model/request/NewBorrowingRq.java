package com.parkee.library_management.model.request;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

/**
 * @author Afif Rana M
 * on 24/02/2025
 */
@Data
public class NewBorrowingRq {
    private Long borrowerId;
    private String bookIsbn;
    private LocalDate returnDeadline;
}