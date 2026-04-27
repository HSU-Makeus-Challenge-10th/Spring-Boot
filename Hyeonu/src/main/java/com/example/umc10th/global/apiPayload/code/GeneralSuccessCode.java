package com.example.umc10th.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter // 인터페이스에 있는 메소드 자동으로 만들어줌
@RequiredArgsConstructor // final이 붙은 필드들로 생성자 자동 생성

public enum GeneralSuccessCode implements BaseSuccessCode{

    OK(HttpStatus.OK,
            "COMMON200_1",
            "성공적으로 요청을 처리했습니다."),;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
