package com.automobiles.exception;

public class ThereIsNoSuchAutoException extends RuntimeException {

    public ThereIsNoSuchAutoException(String message) {
        super(message);
    }
}
