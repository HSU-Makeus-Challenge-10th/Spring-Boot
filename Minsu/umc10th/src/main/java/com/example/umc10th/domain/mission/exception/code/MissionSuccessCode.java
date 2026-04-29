package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "MISSION200_1", "성공"),
    STARTED(HttpStatus.CREATED, "MISSION201_1", "미션 도전이 시작되었습니다."),
    SUCCESS_REQUESTED(HttpStatus.OK, "MISSION200_2", "미션 성공 처리되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
