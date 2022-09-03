package com.web.backend.controllers;

import com.web.backend.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.NotActiveException;

@RestControllerAdvice
public class GlobalControllerAdvice {

    //Globally handles all NotFoundExceptions that occur in service classes.
    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException ex) {
        return "Requested resource was not found!";
        //TODO: More standardized error message?
    }
}
