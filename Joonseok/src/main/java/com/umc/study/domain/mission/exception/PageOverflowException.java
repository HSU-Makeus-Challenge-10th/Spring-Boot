package com.umc.study.domain.mission.exception;

import com.umc.study.domain.mission.exception.code.MissionErrorCode;
import com.umc.study.global.apiPayload.exception.BaseException;

public class PageOverflowException extends BaseException {
    public PageOverflowException() {
        super(MissionErrorCode.PAGE_OVERFLOW);
    }
}
