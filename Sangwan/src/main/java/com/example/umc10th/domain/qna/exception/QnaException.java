package com.example.umc10th.domain.qna.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class QnaException extends ProjectException {
    public QnaException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
