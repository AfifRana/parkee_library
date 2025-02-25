package com.parkee.library_management.model.request;

import lombok.Data;

/**
 * @author Afif Rana M
 * on 24/02/2025
 */
@Data
public class FinishBorrowingRq {
    private Long borrowerId;
    private String bookIsbn;
}
