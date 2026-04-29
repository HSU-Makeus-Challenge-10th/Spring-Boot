package com.study.UMC10.domain.review.code;

import com.study.UMC10.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATED(HttpStatus.CREATED,
            "REVIEW201_1",
            "리뷰가 성공적으로 작성되었습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}