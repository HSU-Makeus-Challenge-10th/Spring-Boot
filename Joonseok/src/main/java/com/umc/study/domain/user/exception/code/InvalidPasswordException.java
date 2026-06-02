package com.umc.study.domain.user.exception.code;

import com.umc.study.global.apiPayload.exception.BaseException;

public class InvalidPasswordException extends BaseException {
    public InvalidPasswordException() {
        super(UserErrorCode.PASSWORD_INVALID);
    }
}
