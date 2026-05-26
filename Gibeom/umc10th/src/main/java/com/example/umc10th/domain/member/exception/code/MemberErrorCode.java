package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "COMMON404_1",
            "해당 사용자를 찾을 수 없습니다."
    ),
    EMAIL_DUPLICATED(HttpStatus.CONFLICT,
            "COMMON404_2",
            "해당 사용자를 찾을 수 없습니다."
    ),
    NICKNAME_DUPLICATED(HttpStatus.CONFLICT,
            "COMMON409_2",
            "이미 존재하는 닉네임입니다."
    ),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,
            "MEMBER401_1",
            "비밀번호가 올바르지 않습니다."
    ),
    NOT_SUPPORT_SOCIAL_PROVIDER(HttpStatus.BAD_REQUEST,
            "404_3",
            "제공하지 않는 인증자입니다."

    ),
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
