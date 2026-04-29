package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "REVIEW200_1", "성공"),
    CREATED(HttpStatus.CREATED, "REVIEW201_1", "리뷰가 등록되었습니다."),
    DELETED(HttpStatus.OK, "REVIEW200_2", "리뷰가 삭제되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
