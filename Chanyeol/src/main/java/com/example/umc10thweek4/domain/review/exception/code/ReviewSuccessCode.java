package com.example.umc10thweek4.domain.review.exception.code;

import com.example.umc10thweek4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "REVIEW200_1",
            "요청 성공"),
    CREATE_SUCCESS(HttpStatus.CREATED,
            "REVIEW201_1",
            "리뷰 등록 성공"),
    LIST_SUCCESS(HttpStatus.OK,
            "REVIEW200_2",
            "리뷰 목록 조회 성공"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
