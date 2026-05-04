package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION_404", "미션 정보를 찾을 수 없습니다."),
    MEMBER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION_4041", "회원이 수행 중인 미션을 찾을 수 없습니다."),
    INVALID_MISSION_STATUS(HttpStatus.BAD_REQUEST, "MISSION_4001", "잘못된 미션 상태값입니다. (INPROGRESS, COMPLETE 중 하나여야 함)"),
    MISSION_ALREADY_COMPLETED(HttpStatus.CONFLICT, "MISSION_4002", "이미 성공 요청 중이거나 완료된 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
