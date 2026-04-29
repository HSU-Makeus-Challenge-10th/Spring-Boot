package com.example.umc10thweek4.domain.ask.exception;

import com.example.umc10thweek4.global.apiPayload.code.BaseErrorCode;
import com.example.umc10thweek4.global.apiPayload.exception.ProjectException;

public class AskException extends ProjectException {
    public AskException(BaseErrorCode message) {
        super(message);
    }
}
