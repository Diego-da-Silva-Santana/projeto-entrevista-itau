package com.desafioitau.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> resourceNotFoundException(ResourceNotFoundException ex) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        Error error = new Error(status.value(), ex.getMessage());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ResourceNotValidException.class)
    public ResponseEntity<Error> resourceNotValidException(ResourceNotValidException ex) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        Error error = new Error(status.value(), ex.getMessage());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        List<String> mensagens = fieldErrors.stream().map(this::getMensagemErro).collect(Collectors.toList());

        HttpStatus status = HttpStatus.BAD_REQUEST;
        Error error = new Error(status.value(), mensagens.toString());

        return ResponseEntity.status(status).body(error);
    }

    private String getMensagemErro(FieldError fieldError) {
        return String.format(" %s: %s", fieldError.getField(), fieldError.getDefaultMessage());
    }
}
