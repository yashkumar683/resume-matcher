package com.parser.parser_service.Exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.parser.parser_service.DTO.ErrorResponse;

@ControllerAdvice
public class FormatException {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                         .body(new ErrorResponse(e.getMessage(), Instant.now()));

    }
}
