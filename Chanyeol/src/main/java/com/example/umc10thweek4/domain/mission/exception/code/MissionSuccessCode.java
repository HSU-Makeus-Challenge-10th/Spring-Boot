package com.example.umc10thweek4.domain.mission.exception.code;

import com.example.umc10thweek4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.OK, "MISSION201_1", "미션 생성 성공"),
    OK(HttpStatus.OK, "MISSION200_1", "요청 성공"),
    HOME_SUCCESS(HttpStatus.OK, "MISSION200_2", "홈 화면 조회 성공"),
    MY_MISSION_SUCCESS(HttpStatus.OK, "MISSION200_3", "내 미션 목록 조회 성공"),
    STORE_MISSION_SUCCESS(HttpStatus.OK, "MISSION200_4", "가게 미션 목록 조회 성공"),
    MISSION_DETAIL_SUCCESS(HttpStatus.OK, "MISSION200_5", "미션 상세 조회 성공"),
    MISSION_COMPLETE_SUCCESS(HttpStatus.OK, "MISSION204_1", "미션 완료 처리 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}