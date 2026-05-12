package com.study.UMC10.domain.mission.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class MissionRequestDto {

    /*
    * NOTE:
    * GET 요청에 Request Body를 사용하지 않지만 미션 요구사항에 따라 Request Body로 userId를 전달받음
    */
    @Schema(description = "진행 중인 미션 조회 요청")
    public record GetMyMissionsDto(
            @Schema(description = "조회할 유저 ID", example = "1")
            Long userId
    ) {
    }
}