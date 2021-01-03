package com.rindus.handlers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvise {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Rindus5Exception> handleGenericNotFoundException(Exception e) {
        Rindus5Exception error = new Rindus5Exception(e.getMessage());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
