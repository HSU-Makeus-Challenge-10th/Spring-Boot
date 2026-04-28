package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "해당 리뷰를 찾을 수 없습니다."),
    REVIEW_NOT_OWNED(HttpStatus.FORBIDDEN, "REVIEW403_1", "해당 리뷰에 대한 권한이 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
