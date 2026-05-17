package com.umc.study.domain.review.exception.code;

import com.umc.study.global.apiPayload.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseResponseCode {

    REVIEW_CREATED(
            HttpStatus.CREATED,
            "REVIEW201_1",
            "리뷰 작성에 성공했습니다."
    ),
    MY_REVIEW_OK(
            HttpStatus.OK,
            "REVIEW200_1",
            "내 리뷰 조회에 성공했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
