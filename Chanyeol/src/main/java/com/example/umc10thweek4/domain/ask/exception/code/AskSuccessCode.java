package com.example.umc10thweek4.domain.ask.exception.code;

import com.example.umc10thweek4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AskSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "ASK200_1", "요청 성공"),
    CREATE_SUCCESS(HttpStatus.CREATED, "ASK201_1", "문의 등록 성공"),
    LIST_SUCCESS(HttpStatus.OK, "ASK202_1", "문의 목록 조회 성공"),
    DETAIL_SUCCESS(HttpStatus.OK, "ASK203_1", "문의 상세 조회 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}