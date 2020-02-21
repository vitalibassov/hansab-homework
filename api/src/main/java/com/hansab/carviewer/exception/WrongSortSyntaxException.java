package com.hansab.carviewer.exception;

public class WrongSortSyntaxException extends RuntimeException {
    public WrongSortSyntaxException(String message, String sort) {
        super(String.format("%s sort=%s", message, sort));
    }
}
