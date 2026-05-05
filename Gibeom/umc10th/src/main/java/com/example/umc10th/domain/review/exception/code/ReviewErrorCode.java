package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "리뷰를 찾을 수 없음"
    ),

    ALREADY_REVIEWED(HttpStatus.CONFLICT,
            "REVIEW409_1",
            "이미 리뷰를 작성한 미션입니다."
    ),;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
