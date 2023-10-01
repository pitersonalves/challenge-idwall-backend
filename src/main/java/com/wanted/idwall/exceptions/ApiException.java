package com.wanted.idwall.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {

    private final String code;

    private final String description;

    private final HttpStatus statusCode;

    public ApiException(String code, String description, HttpStatus statusCode) {
        super(description);
        this.code = code;
        this.description = description;
        this.statusCode = statusCode;

    }

    public  ApiException(String code, String description, HttpStatus statusCode, Throwable cause) {
        super(description, cause);
        this.code = code;
        this.description = description;
        this.statusCode = statusCode;
    }
}
