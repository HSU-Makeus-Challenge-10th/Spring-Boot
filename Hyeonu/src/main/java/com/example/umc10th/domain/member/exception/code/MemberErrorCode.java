package com.example.umc10th.domain.member.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, " MEMBER404_1","해당 사용자를 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.NOT_FOUND,"MEMBER404_2","비밀번호가 일치하지 않습니다." ),
    NOT_SUPPORT_SOCIAL_PROVIDER(HttpStatus.NOT_FOUND,"MEMBER404_3" ,"지원하지 않는 소셜 로그인입니다." );

    private final HttpStatus status;
    private final String code;
    private final String message;

}
