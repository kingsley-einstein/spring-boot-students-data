package com.practice.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RecordExceptionHandler {
    @ExceptionHandler(value = RecordNotFoundException.class)
    public String exception(RecordNotFoundException exception) {
        return exception.getMessage();
    }
}