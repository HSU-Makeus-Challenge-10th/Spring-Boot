package com.example.umc10th.domain.store.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FoodSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK,
            "FOOD200_1",
            "음식 조회 성공"
    ),;
        private final HttpStatus status;
        private final String code;
        private final String message;


}
