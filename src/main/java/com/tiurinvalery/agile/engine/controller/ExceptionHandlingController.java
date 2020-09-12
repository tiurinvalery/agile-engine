package com.tiurinvalery.agile.engine.controller;

import com.tiurinvalery.agile.engine.exception.AccountNotFoundException;
import com.tiurinvalery.agile.engine.exception.BadRequestException;
import com.tiurinvalery.agile.engine.exception.InternalServerError;
import com.tiurinvalery.agile.engine.exception.NegativeBalanceException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlingController {

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler({AccountNotFoundException.class})
    public ResponseEntity<Object> accountNotFoundException(HttpServletRequest request, AccountNotFoundException accountNotFoundException) {
        return new ResponseEntity<>(accountNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> badRequest(HttpServletRequest request, BadRequestException badRequest) {
        return new ResponseEntity<>(badRequest.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler({NegativeBalanceException.class})
    public ResponseEntity<Object> negativeBalance(HttpServletRequest request, NegativeBalanceException negativeBalance) {
        return new ResponseEntity<>(negativeBalance.getLocalizedMessage(), HttpStatus.CONFLICT);
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler({InternalServerError.class})
    public ResponseEntity<Object> internalServerError(HttpServletRequest request, InternalServerError error) {
        return new ResponseEntity<>("We catch exception on server side, try your request latter", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Order
    @ExceptionHandler({Throwable.class})
    public ResponseEntity<Object> uncatchedError(HttpServletRequest request, Throwable throwable) {
        return new ResponseEntity<>("Something went wrong, try your request latter", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
