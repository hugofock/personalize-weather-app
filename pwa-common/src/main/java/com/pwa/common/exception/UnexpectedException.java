package com.pwa.common.exception;

public class UnexpectedException extends Exception {

    public UnexpectedException(String message) {
        super(message);
    }

    public UnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }


}
