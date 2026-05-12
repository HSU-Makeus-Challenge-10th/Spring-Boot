package com.example.umc10thweek4.domain.mission.exception;

import com.example.umc10thweek4.global.apiPayload.code.BaseErrorCode;
import com.example.umc10thweek4.global.apiPayload.exception.ProjectException;

public class MissionException extends ProjectException {
    public MissionException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}