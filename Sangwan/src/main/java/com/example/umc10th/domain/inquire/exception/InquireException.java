package com.example.umc10th.domain.inquire.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class InquireException extends ProjectException {
    public InquireException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
