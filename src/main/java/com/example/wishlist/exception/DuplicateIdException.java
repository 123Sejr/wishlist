package com.example.wishlist.exception;

public class DuplicateIdException extends RuntimeException {
    public DuplicateIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
