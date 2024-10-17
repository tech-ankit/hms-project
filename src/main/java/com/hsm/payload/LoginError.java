package com.hsm.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class LoginError {
    private String message;
    private HttpStatus httpStatus;
    private String dateTime ;
}
