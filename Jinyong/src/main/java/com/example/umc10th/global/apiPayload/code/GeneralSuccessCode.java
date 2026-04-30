package com.example.umc10th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseErrorCode {

    OK(HttpStatus.OK,
            "COMMON200_1",
            "성공적으로 요청을 처리했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
