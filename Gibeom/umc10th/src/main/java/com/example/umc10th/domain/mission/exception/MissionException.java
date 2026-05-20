package com.example.umc10th.domain.mission.exception;

import com.example.umc10th.global.apiPayload.Exception.ProjectException;
import com.example.umc10th.global.apiPayload.code.BaseErrorCode;

public class MissionException extends ProjectException {

    public MissionException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
