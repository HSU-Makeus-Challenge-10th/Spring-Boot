package com.example.umc10thweek4.domain.mission.exception.code;

import com.example.umc10thweek4.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "존재하지 않는 미션입니다."),
    USER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_2", "존재하지 않는 유저 미션입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_3", "존재하지 않는 회원입니다."),
    ALREADY_PARTICIPATED(HttpStatus.CONFLICT, "MISSION409_1", "이미 참여한 미션입니다."),
    UNAUTHORIZED_MISSION(HttpStatus.FORBIDDEN, "MISSION403_1", "해당 미션에 대한 권한이 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}