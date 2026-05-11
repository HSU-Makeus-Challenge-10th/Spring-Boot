package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "MISSION200_1",
            "성공적으러 미션을 조회했습니다."),

    CHALLENGE_SUCCESS(HttpStatus.CREATED,
            "MISSION201_1",
            "미션 도전이 성공적으로 완료되었습니다."),
    CHALLENGE_UPDATE(HttpStatus.OK,
            "MISSION200_2",
                "미션 상태가 성공적으로 변경되었습니다."
            ),
    CREATED(HttpStatus.CREATED,
            "MISSION200_3",
            "성공적으로 미션을 생성했습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
