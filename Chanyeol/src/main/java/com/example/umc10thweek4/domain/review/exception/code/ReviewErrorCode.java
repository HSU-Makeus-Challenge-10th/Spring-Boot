package com.example.umc10thweek4.domain.review.exception.code;

import com.example.umc10thweek4.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "존재하지 않는 리뷰입니다."),
    REVIEW_ALREADY_EXISTS(HttpStatus.CONFLICT, "REVIEW409_1", "이미 리뷰를 작성했습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_2", "존재하지 않는 회원입니다."),
    USER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_3", "존재하지 않는 유저 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}