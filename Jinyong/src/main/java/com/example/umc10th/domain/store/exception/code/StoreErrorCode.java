package com.example.umc10th.domain.store.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "STORE404_1",
            "해당 가게를 찾을 수 없습니다."),
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REGION404_1",
            "해당 지역을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public HttpStatus getStatus() {
        return httpStatus;
    }
}