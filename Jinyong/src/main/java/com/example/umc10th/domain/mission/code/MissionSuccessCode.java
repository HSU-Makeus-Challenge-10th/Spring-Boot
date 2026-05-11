package com.example.umc10th.domain.mission.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 미션을 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}