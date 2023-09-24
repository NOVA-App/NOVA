package com.sehbeomschool.nova.domain.saving.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SavingResponseMessage {

    SUCCESS_GET_SAVING_INFO("금융 현황 조회 완료"),
    SUCCESS_ADD_INSTALLMENT("적금 가입 완료");

    private final String message;
}
