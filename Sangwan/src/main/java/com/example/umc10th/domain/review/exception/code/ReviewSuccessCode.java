package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATED(HttpStatus.CREATED, "REVIEW_201", "리뷰 등록이 완료되었습니다."),
    REVIEW_LIST(HttpStatus.OK, "REVIEW_200", "리뷰 목록 조회가 완료되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
