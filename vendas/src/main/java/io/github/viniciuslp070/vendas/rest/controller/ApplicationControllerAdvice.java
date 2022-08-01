package io.github.viniciuslp070.vendas.rest.controller;

import io.github.viniciuslp070.vendas.exception.BusinessRuleException;
import io.github.viniciuslp070.vendas.exception.OrderNotFoundException;
import io.github.viniciuslp070.vendas.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBusinessRuleException(BusinessRuleException exception) {
        String message = exception.getMessage();
        return new ApiErrors(message);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleOrderNotFoundException(OrderNotFoundException exception) {
        return new ApiErrors(exception.getMessage());
    }
}
