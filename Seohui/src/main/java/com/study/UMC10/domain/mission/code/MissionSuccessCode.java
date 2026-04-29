package com.study.UMC10.domain.mission.code;

import com.study.UMC10.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "MISSION200_1",
            "미션 관련 요청이 성공했습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}