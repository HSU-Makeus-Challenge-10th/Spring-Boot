package com.example.umc10th.domain.store.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK,
            "STORE200_1",
            "가게 조회 성공"),;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
