package com.example.umc10th.domain.inquire.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum InquireSuccessCode implements BaseSuccessCode {

    INQUIRE_CREATED(HttpStatus.CREATED, "INQUIRE_201", "문의 등록이 완료되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
