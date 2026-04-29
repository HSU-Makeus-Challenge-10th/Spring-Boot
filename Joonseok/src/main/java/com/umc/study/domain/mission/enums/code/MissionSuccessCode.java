package com.umc.study.domain.mission.enums.code;

import com.umc.study.global.apiPayload.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseResponseCode {

    MISSION_GET_OK(HttpStatus.OK,
            "MISSION200_1",
            "근처 미션 조회에 성공했습니다."),
    MISSION_COMPLETE_REQUEST(HttpStatus.OK,
            "MISSION200_2",
            "미션 성공이 요청되었습니다."),
    MISSION_COMPLETED(HttpStatus.OK,
            "MISSION200_3",
            "미션이 성공처리 되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
