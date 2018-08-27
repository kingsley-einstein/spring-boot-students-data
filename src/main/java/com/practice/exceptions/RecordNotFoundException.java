package com.practice.exceptions;

public class RecordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    @Override
    public String getMessage() {
        return "No record found";
    }
}