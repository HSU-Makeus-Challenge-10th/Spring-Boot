package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "MEMBER200_1", "성공적으로 유저를 조회했습니다."),
    CREATED(HttpStatus.CREATED, "MEMBER201_1", "회원가입 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
