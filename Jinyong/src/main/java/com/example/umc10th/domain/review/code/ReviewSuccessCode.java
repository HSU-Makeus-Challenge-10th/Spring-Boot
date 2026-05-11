package com.example.umc10th.domain.review.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
        "REVIEW_200_1",
        "성공적으로 리뷰를 조회했습다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
