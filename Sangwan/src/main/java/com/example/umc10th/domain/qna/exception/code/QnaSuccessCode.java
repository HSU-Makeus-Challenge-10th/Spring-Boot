package com.example.umc10th.domain.qna.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum QnaSuccessCode implements BaseSuccessCode {

    QNA_CREATED(HttpStatus.CREATED, "QNA_201", "문의 등록이 완료되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
