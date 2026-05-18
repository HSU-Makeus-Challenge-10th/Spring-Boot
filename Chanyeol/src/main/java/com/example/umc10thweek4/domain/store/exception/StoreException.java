package com.example.umc10thweek4.domain.store.exception;

import com.example.umc10thweek4.global.apiPayload.code.BaseErrorCode;
import com.example.umc10thweek4.global.apiPayload.exception.ProjectException;

public class StoreException extends ProjectException {
    public StoreException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}