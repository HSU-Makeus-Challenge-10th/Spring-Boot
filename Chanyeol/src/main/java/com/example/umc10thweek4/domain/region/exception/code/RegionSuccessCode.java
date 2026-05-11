package com.example.umc10thweek4.domain.region.exception.code;

import com.example.umc10thweek4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RegionSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "REGION200", "지역 조회 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}