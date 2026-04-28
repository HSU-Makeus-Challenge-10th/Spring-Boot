package com.example.umc10th.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "COMMON404_1",
            "해당 사용자를 찾을 수 없습니다."
    ),
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
