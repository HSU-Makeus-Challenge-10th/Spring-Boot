package com.umc.study.domain.user.exception;

import com.umc.study.domain.user.exception.code.UserErrorCode;
import com.umc.study.global.apiPayload.exception.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
