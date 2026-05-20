package com.example.umc10th.domain.store.exception;

import com.example.umc10th.domain.store.exception.code.FoodErrorCode;
import com.example.umc10th.global.apiPayload.Exception.ProjectException;

public class FoodException extends ProjectException {
    public FoodException(FoodErrorCode errorCode) {
        super(errorCode);
    }
}
