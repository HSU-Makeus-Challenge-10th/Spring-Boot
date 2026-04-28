package com.umc.study.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GeneralSuccessCode implements BaseResponseCode{

    OK(HttpStatus.OK,
            "COMMON200_1",
            "성공적으로 요청을 처리했습니다"),
    CREATED(HttpStatus.CREATED,
            "COMMON201_1",
            "리소스 생성에 성공했습니다");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
