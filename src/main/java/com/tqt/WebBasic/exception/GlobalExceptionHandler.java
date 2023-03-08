package com.tqt.WebBasic.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler({ EmptyResultDataAccessException.class, EntityNotFoundException.class, NullPointerException.class})
    public ResponseEntity<String> emptyResult(Exception e){
        return ResponseEntity.status(500).body(e.getMessage());
    }

}
