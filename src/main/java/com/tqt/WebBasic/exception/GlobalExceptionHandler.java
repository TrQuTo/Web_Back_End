package com.tqt.WebBasic.exception;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({EmptyResultDataAccessException.class, EntityNotFoundException.class, NullPointerException.class, EntityExistsException.class,
            SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<String> serverResult(Exception e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> clientResult(Exception e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
