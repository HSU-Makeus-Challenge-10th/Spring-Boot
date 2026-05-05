package com.example.umc10thweek4.domain.review.exception.code;

import com.example.umc10thweek4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "REVIEW200_1",
            "성공적으로 문의를 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
