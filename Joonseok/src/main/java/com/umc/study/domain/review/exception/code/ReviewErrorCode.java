package com.umc.study.domain.review.exception.code;

import com.umc.study.global.apiPayload.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseResponseCode {

    ILLEGAL_REVIEW_TYPE(HttpStatus.BAD_REQUEST, "ILLEGAL_REVIEW_TYPE_400", "서버 내에 선언된 값이 아닙니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
