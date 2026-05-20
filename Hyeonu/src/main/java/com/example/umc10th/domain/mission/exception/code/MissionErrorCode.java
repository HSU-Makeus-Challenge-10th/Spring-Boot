package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "미션 조회에 실패했습니다."),
    QUERY_NOT_VALID(HttpStatus.BAD_REQUEST,
            "MISSION400_2",
            "유효하지 않은 쿼리 파라미터입니다.")
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}