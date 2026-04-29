package com.example.umc10th.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@RequiredArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode{

    BAD_REQUEST(HttpStatus.BAD_REQUEST,
            "COMMON400_1",
            "잘못된 요청입니다."),
     UNAUTHORTIZED(HttpStatus.UNAUTHORIZED,
             "COMMON401_1",
             "인증되지 않았습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN,
            "COMMON403_1",
            "접근이 금지되었습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "COMMON404_1",
            "해당 리소스를 찾을 수 없습니다."
    ),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED,
            "COMMON405",
            "허용되지 않은 메소드입니다."

    ),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
            "COMMON500",
            "서버 내부 오류입니다."
    ),
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;

}
