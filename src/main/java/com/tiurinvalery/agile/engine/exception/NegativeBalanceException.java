package com.tiurinvalery.agile.engine.exception;

public class NegativeBalanceException extends RuntimeException {
    public NegativeBalanceException(String message,Throwable cause) {
        super(message,cause);
    }

    public NegativeBalanceException(String message) {
        super(message);
    }
}
