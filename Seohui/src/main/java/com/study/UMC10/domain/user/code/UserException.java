package com.study.UMC10.domain.user.code;

import com.study.UMC10.global.apiPayload.code.BaseErrorCode;
import com.study.UMC10.global.apiPayload.exception.GeneralException;

public class UserException extends GeneralException{
    public UserException(BaseErrorCode errorcode) {
        super(errorcode);
    }
}
