package com.umc.study.domain.user.exception.code;

import com.umc.study.global.apiPayload.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseResponseCode {
    USER_NOT_FOUND("USER_404_1", HttpStatus.NOT_FOUND, "해당 멤버를 찾는데 실패했습니다."),
    PASSWORD_INVALID("USER_400_1", HttpStatus.BAD_REQUEST, "비밀번호가 잘못되었습니다.");

    private final String code;
    private final HttpStatus status;
    private final String message;
}
