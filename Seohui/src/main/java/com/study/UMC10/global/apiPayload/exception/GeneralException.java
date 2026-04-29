package com.study.UMC10.global.apiPayload.exception;

import com.study.UMC10.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GeneralException extends RuntimeException {
    private final BaseErrorCode errorCode;
}
