package com.example.umc10th.domain.review.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode {
    REVIEW_NOT_CREATED(HttpStatus.NOT_FOUND, "REVIEW404_1", "해당 리뷰를 찾을 수 없습니다."),
    QUERY_NOT_VALID(HttpStatus.BAD_REQUEST, "REVIEW400_1", "유효하지 않은 쿼리 파라미터입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}