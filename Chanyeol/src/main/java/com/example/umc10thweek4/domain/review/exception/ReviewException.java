package com.example.umc10thweek4.domain.review.exception;

import com.example.umc10thweek4.global.apiPayload.code.BaseErrorCode;
import com.example.umc10thweek4.global.apiPayload.exception.ProjectException;

public class ReviewException extends ProjectException {
    public ReviewException(BaseErrorCode message) {
        super(message);
    }
}
