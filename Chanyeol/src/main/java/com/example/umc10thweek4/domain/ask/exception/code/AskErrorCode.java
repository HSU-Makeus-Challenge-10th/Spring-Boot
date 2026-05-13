package com.example.umc10thweek4.domain.ask.exception.code;

import com.example.umc10thweek4.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AskErrorCode implements BaseErrorCode {

    ASK_NOT_FOUND(HttpStatus.NOT_FOUND, "ASK404_1", "존재하지 않는 문의입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "ASK404_2", "존재하지 않는 회원입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}