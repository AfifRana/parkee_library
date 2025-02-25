package com.parkee.library_management.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Afif Rana M
 * on 25/02/2025
 */
@Data
@NoArgsConstructor
public class GeneralRs {
    private int statusCode;
    private String message;
    private Object data;
}
