package com.webdev.webservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> userNotFoundException(UserNotFoundException exception, WebRequest request) {
        ErrorDetails error = new ErrorDetails();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setPath(request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> MethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception, WebRequest request) {
        ErrorDetails error = new ErrorDetails();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setPath(request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> EmailAlreadyExists(EmailAlreadyExistsException exception, WebRequest request) {
        ErrorDetails error = new ErrorDetails();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setPath(request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
