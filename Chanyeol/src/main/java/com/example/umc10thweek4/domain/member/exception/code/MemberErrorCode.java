package com.example.umc10thweek4.domain.member.exception.code;

import com.example.umc10thweek4.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "MEMBER409_1", "이미 사용 중인 이메일입니다."),
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "MEMBER409_2", "이미 사용 중인 닉네임입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "존재하지 않는 회원입니다."),
    NOTICE_SETTING_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_2", "알림 설정 정보가 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}