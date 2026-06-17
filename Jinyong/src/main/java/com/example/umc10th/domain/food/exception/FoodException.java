package com.example.umc10th.domain.food.exception;

import com.example.umc10th.domain.food.exception.code.FoodErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class FoodException extends ProjectException {
    public FoodException(FoodErrorCode errorCode) {
        super(errorCode);
    }
}
