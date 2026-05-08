package com.umc.study.domain.user.exception.code;

import com.umc.study.global.apiPayload.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseResponseCode {
    USER_NOT_FOUND("USER_NOT_FOUND_404", HttpStatus.NOT_FOUND, "해당 멤버를 찾는데 실패했습니다.");

    private final String code;
    private final HttpStatus status;
    private final String message;
}
