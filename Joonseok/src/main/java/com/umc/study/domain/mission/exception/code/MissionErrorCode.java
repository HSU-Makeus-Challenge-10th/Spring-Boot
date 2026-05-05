package com.umc.study.domain.mission.exception.code;

import com.umc.study.global.apiPayload.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseResponseCode {
    PAGE_OVERFLOW("PAGE_OVERFLOW_400", 400, "해당 페이지는 리소스의 개수를 초과하여 조회하고 있습니다.");

    private  final String code;
    private final Integer status;
    private final String message;
}
