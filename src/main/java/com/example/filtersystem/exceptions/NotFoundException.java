package com.example.filtersystem.exceptions;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7229660770666345362L;

    public NotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
