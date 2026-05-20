package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    CREATED(HttpStatus.CREATED,
            "REVIEW200_1",
            "성공적으로 리뷰가 생성되었습니다."),
    OK(HttpStatus.OK,
            "REVIEW200_2",
            "성공적으로 리뷰가 조회되었습니다."),
            ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
