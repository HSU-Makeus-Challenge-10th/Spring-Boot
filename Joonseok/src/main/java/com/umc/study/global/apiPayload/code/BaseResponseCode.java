package com.umc.study.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseResponseCode {

    // 모든 응답에 같은 interface를 사용하므로, 해당 인터페이스를 Error 전용이 아닌, 공통 응답 인터페이스로 전환
    HttpStatus getStatus();
    String getCode();
    String getMessage();
}
