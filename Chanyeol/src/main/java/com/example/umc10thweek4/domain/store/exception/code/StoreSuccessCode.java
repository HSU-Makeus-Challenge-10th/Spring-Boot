package com.example.umc10thweek4.domain.store.exception.code;

import com.example.umc10thweek4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "STORE200", "가게 조회 성공"),
    STORE_MISSIONS_SUCCESS(HttpStatus.OK, "STORE201", "가게 미션 목록 조회 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}