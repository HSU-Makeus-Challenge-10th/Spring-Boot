package com.example.umc10thweek4.domain.region.exception;

import com.example.umc10thweek4.global.apiPayload.code.BaseErrorCode;
import com.example.umc10thweek4.global.apiPayload.exception.ProjectException;

public class RegionException extends ProjectException {
    public RegionException(BaseErrorCode message) {
        super(message);
    }
}
