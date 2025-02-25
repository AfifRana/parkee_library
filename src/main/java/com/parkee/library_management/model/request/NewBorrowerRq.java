package com.parkee.library_management.model.request;

import lombok.Data;

/**
 * @author Afif Rana M
 * on 25/02/2025
 */
@Data
public class NewBorrowerRq {
    private String ktpNumber;
    private String name;
    private String email;
}
