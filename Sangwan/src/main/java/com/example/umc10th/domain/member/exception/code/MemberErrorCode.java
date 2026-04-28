package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_404", "해당 사용자를 찾을 수 없습니다."),
    MEMBER_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER_4001", "이미 가입된 회원 정보가 존재합니다."),
    INVALID_BIRTHDAY_FORMAT(HttpStatus.BAD_REQUEST, "MEMBER_4002", "생년월일 형식이 올바르지 않습니다. (YYYY-MM-DD)");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
