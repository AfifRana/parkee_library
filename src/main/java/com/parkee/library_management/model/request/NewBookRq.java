package com.parkee.library_management.model.request;

import lombok.Data;

/**
 * @author Afif Rana M
 * on 25/02/2025
 */
@Data
public class NewBookRq {
    private String title;
    private String isbn;
    private int stock;
}
