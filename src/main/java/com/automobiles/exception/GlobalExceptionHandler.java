package com.automobiles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ThereIsNoSuchAutoException.class)
    public ResponseEntity<String> handleNoSuchAutoException(ThereIsNoSuchAutoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage() + "\nStack Trace: " + getStackTrace(ex));
    }

    private String getStackTrace(Throwable throwable) {
        StringBuilder result = new StringBuilder();
        for (StackTraceElement element : throwable.getStackTrace()) {
            result.append(element).append("\n");
        }
        return result.toString();
    }
}
