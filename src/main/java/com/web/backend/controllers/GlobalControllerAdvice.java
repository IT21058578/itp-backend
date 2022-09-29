package com.web.backend.controllers;

import com.web.backend.exception.AlreadyExistsException;
import com.web.backend.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException ex) {
        return "Requested resource was not found!" + ex.getMessage();
    }

    @ExceptionHandler(value = {IllegalStateException.class})
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public String handleIllegalStateException(IllegalStateException ex) {
        return "Requested resource was not found!" + ex.getMessage();
    }

    @ExceptionHandler(value = {AlreadyExistsException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public String handleAlreadyExistsException(AlreadyExistsException ex) {
        return "Attempt to create a resource that already exists!" + ex.getMessage();
    }

    @ExceptionHandler(value = {ServerWebInputException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleServerWebInputException(ServerWebInputException ex) {
        return "Wrong params for the specific endpoint or params does not match!" + ex.getMessage();
    }
}
