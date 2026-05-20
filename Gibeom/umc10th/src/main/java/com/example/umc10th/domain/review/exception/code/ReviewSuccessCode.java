package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK,
            "REVIEW200_1",
            "리뷰 조회 성공"
    ),
    WRITE_SUCCESS(HttpStatus.CREATED,
            "REVIEW201_1",
            "리뷰 작성 성공"
    ),;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
