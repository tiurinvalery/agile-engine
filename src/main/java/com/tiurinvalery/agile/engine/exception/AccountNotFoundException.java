package com.tiurinvalery.agile.engine.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message,Throwable cause) {
        super(message,cause);
    }

    public AccountNotFoundException(String message) {
        super(message);
    }
}
