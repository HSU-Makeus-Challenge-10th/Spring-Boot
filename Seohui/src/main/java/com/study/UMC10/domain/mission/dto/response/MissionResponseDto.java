package com.study.UMC10.domain.mission.dto.response;

import lombok.Builder;
import java.util.List;

public class MissionResponseDto {

    // 미션 목록
    @Builder
    public record MissionListDto(
            List<MissionDetailDto> missions
    ) {
    }

    // 미션 목록 내부 개별 미션 상세
    @Builder
    public record MissionDetailDto(
            Long missionId,
            Integer rewardPoints,
            String storeName,
            String description,
            String status
    ) {
    }

    // 미션 성공
    @Builder
    public record MissionCompleteResultDto(
            Long missionId,
            String status
    ) {
    }
}