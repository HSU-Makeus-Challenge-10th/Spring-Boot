package com.example.umc10thweek4.domain.member.exception.code;

import com.example.umc10thweek4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "MEMBER200_1", "요청 성공"),
    SIGN_UP_SUCCESS(HttpStatus.CREATED, "MEMBER201_1", "회원가입 성공"),
    MY_PAGE_SUCCESS(HttpStatus.OK, "MEMBER202_1", "마이페이지 조회 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}