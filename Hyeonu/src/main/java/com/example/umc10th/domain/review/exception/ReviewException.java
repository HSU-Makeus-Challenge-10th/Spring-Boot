package com.example.umc10th.domain.review.exception;

import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import lombok.Getter;

@Getter
public class ReviewException extends RuntimeException {
    // 에러 정보를 저장할 필드 추가
    private final ReviewErrorCode  errorCode;
    public ReviewException(ReviewErrorCode errorCode) {
        // 부모 RuntimeException에 에러 메세지 전달
        super(errorCode.getMessage());
        this.errorCode=errorCode;
    }
}
