package com.tiurinvalery.agile.engine.exception;

public class InternalServerError extends RuntimeException {
    public InternalServerError(String message,Throwable cause) {
        super(message,cause);
    }

    public InternalServerError(String message) {
        super(message);
    }
}
