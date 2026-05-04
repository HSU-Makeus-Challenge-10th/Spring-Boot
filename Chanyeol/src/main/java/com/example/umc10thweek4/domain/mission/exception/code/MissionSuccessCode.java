package com.example.umc10thweek4.domain.mission.exception.code;

import com.example.umc10thweek4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 문의를 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
