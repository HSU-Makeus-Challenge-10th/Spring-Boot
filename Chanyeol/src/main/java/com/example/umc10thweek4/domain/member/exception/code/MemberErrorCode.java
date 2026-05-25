package com.example.umc10thweek4.domain.member.exception.code;

import com.example.umc10thweek4.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    INVALID_BIRTHDAY(HttpStatus.BAD_REQUEST, "MEMBER400_1", "생년월일은 yyyy-MM-dd 형식이어야 합니다."),
    INVALID_GENDER(HttpStatus.BAD_REQUEST, "MEMBER400_2", "성별은 MALE, FEMALE, NONE 중 하나여야 합니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "MEMBER409_1", "이미 사용 중인 이메일입니다."),
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "MEMBER409_2", "이미 사용 중인 닉네임입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "존재하지 않는 회원입니다."),
    NOTICE_SETTING_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_2", "알림 설정 정보가 없습니다."),
    NOT_SUPPORT_SOCIAL_PROVIDER(HttpStatus.BAD_REQUEST, "MEMBER400_3", "지원하지 않는 소셜 로그인 제공자입니다."),
    OAUTH_EMAIL_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER400_4", "소셜 계정에서 이메일 정보를 가져올 수 없습니다."),
    OAUTH_REQUIRED_ATTRIBUTE_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER400_5", "소셜 계정에서 필수 정보를 가져올 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
