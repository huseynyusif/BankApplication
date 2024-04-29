package az.unitech.bankapplication.controller;

import az.unitech.bankapplication.exception.ExceptionResponse;
import az.unitech.bankapplication.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handle(UserAlreadyExistsException ex) {
        return new ExceptionResponse(ex.getMessage());
    }
}
