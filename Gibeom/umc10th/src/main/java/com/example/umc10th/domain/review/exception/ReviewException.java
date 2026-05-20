package com.example.umc10th.domain.review.exception;

import com.example.umc10th.global.apiPayload.Exception.ProjectException;
import com.example.umc10th.global.apiPayload.code.BaseErrorCode;

public class ReviewException extends ProjectException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}
