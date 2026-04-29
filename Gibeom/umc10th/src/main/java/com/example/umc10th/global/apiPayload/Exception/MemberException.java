package com.example.umc10th.global.apiPayload.Exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
@Getter
public class MemberException extends ProjectException {
    public MemberException(BaseErrorCode errorCode) {super(errorCode);}

}



