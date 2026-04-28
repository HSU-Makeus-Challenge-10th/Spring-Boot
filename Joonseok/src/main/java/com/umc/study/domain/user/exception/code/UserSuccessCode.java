package com.umc.study.domain.user.exception.code;

import com.umc.study.global.apiPayload.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserSuccessCode implements BaseResponseCode {

    HOME_OK(HttpStatus.OK,
            "USER200_1",
            "홈 화면 조회에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
