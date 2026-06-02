package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import org.springframework.http.HttpStatus;

public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404",
            "해당 사용자를 찾을 수 없습니다."),
    MEMBER_ALREADY_EXISTS(HttpStatus.CONFLICT,
            "MEMBER409_1",
            "이미 존재하는 이메일입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,
            "MEMBER401_1",
            "비밀번호가 일치하지 않습니다."),
    NOT_SUPPORT_SOCIAL_PROVIDER(HttpStatus.BAD_REQUEST,
            "MEMBER400_1",
            "지원하지 않는 소셜 로그인 제공자입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    MemberErrorCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return httpStatus;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}