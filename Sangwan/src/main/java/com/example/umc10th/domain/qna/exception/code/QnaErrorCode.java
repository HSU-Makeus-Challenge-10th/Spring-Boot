package com.example.umc10th.domain.qna.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum QnaErrorCode implements BaseErrorCode {

    QNA_NOT_FOUND(HttpStatus.NOT_FOUND, "QNA_404", "해당 문의를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
