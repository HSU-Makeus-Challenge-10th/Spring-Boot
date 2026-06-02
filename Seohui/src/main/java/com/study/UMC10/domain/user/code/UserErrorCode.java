package com.study.UMC10.domain.user.code;

import com.study.UMC10.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 사용자를 찾을 수 없습니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "USER400_1", "이미 사용 중인 이메일입니다."),
    INVALID_GENDER(HttpStatus.BAD_REQUEST, "USER400_2", "올바르지 않은 성별 형식입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "USER400_3", "비밀번호가 일치하지 않습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}