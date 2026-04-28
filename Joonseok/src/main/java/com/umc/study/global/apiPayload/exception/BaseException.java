package com.umc.study.global.apiPayload.exception;

import com.umc.study.global.apiPayload.code.BaseResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseException extends RuntimeException {

    private final BaseResponseCode errorCode;
}
