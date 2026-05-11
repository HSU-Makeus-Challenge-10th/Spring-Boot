package com.example.umc10th.domain.store.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "Store200_1",
            "성공적으로 스토어를 등록했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
