package com.sehbeomschool.nova.domain.saving.exception;

import com.sehbeomschool.nova.global.error.exception.NotEnoughException;

public class SavingNotEnoughException extends NotEnoughException {

    public SavingNotEnoughException(String message) {
        super(message);
    }

}
