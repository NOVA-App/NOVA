package com.sehbeomschool.nova.global.error.exception;

public class AlreadyExistException extends IllegalArgumentException {

    public AlreadyExistException() {
    }

    public AlreadyExistException(String message) {
        super(message);
    }
}
