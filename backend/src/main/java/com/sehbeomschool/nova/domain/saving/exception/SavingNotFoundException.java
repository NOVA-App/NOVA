package com.sehbeomschool.nova.domain.saving.exception;

import com.sehbeomschool.nova.global.error.exception.NotFoundException;

public class SavingNotFoundException extends NotFoundException {

    public SavingNotFoundException(String message) {
        super(message);
    }

}
