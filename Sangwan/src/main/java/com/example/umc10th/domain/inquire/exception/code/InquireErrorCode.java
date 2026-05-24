package com.example.umc10th.domain.inquire.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum InquireErrorCode implements BaseErrorCode {

    INQUIRE_NOT_FOUND(HttpStatus.NOT_FOUND, "INQUIRE_404", "해당 문의를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
