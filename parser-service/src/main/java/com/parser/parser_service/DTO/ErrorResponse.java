package com.parser.parser_service.DTO;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
     private String message;
    private Instant timestamp;
}
