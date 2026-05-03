package com.example.umc10th.domain.store.exception;

import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class StoreException extends ProjectException {

    public StoreException(StoreErrorCode errorCode) {
        super(errorCode);
    }
}
