package com.sehbeomschool.nova.domain.saving.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SavingExceptionMessage {
    NOT_EXIST_INSTALLMENT("적금이 존재하지 않습니다"),
    NOT_EXIST_INSINTEREST("해당 기간의 적금이 존재하지 않습니다"),
    NOT_ALLOW_MINUS("마이너스 금액으로 변경할 수 없습니다.");

    private final String message;
}
