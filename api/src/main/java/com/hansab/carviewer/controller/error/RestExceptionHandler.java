package com.hansab.carviewer.controller.error;

import com.hansab.carviewer.exception.ResourceNotFoundException;
import com.hansab.carviewer.exception.WrongSortSyntaxException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(NOT_FOUND).body(new ErrorDetails(NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(WrongSortSyntaxException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(WrongSortSyntaxException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(new ErrorDetails(BAD_REQUEST, ex.getMessage()));
    }
}
