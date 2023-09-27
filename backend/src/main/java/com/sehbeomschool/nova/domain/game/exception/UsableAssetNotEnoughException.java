package com.sehbeomschool.nova.domain.game.exception;

import com.sehbeomschool.nova.global.error.exception.NotEnoughException;

public class UsableAssetNotEnoughException extends NotEnoughException {

    public UsableAssetNotEnoughException(String message) {
        super(message);
    }
}
