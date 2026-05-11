package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "해당 미션을 찾을 수 없습니다."),
    ACTIVATED_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_2", "해당 진행 미션을 찾을 수 없습니다."),
    MISSION_ALREADY_STARTED(HttpStatus.CONFLICT, "MISSION409_1", "이미 도전 중인 미션입니다."),
    ALREADY_COMPLETED(HttpStatus.CONFLICT, "MISSION409_2", "이미 완료된 미션입니다."),
    INVALID_APPROVER_CODE(HttpStatus.BAD_REQUEST, "MISSION400_1", "구분번호가 일치하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
