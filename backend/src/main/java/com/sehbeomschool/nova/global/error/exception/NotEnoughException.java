package com.sehbeomschool.nova.global.error.exception;

public class NotEnoughException extends IllegalArgumentException {

    public NotEnoughException() {
    }

    public NotEnoughException(String message) {
        super(message);
    }
}
