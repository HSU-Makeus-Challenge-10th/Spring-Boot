package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_FOUND(HttpStatus.OK, "MISSION_200", "미션 조회가 완료되었습니다."),
    REVIEW_CREATED(HttpStatus.CREATED, "REVIEW_201", "리뷰 등록이 완료되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
