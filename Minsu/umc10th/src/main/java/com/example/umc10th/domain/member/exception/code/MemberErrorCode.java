package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 사용자를 찾을 수 없습니다."),
    FOOD_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_2", "존재하지 않는 음식 종류가 포함되어 있습니다."),
    TERM_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_3", "존재하지 않는 약관이 포함되어 있습니다."),
    MEMBER_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER409_1", "이미 존재하는 이메일입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "MEMBER401_1", "인증되지 않은 사용자입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
