package com.example.umc10th.domain.member.exception;

import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {

    // 에러 정보를 저장할 필드 추가
    private final MemberErrorCode errorCode;

    // MemberError를 인자로 받는 생성자 추가
    public MemberException(MemberErrorCode errorCode) {
        // 부모 클래스(RuntimeException)에게 에러 메시지를 전달
        super(errorCode.getMessage());
        this.errorCode=errorCode;
    }
}
