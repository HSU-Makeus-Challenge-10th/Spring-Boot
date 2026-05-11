package com.example.umc10th.domain.store.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "STORE200_1",
            "성공적으로 가게 목록을 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}