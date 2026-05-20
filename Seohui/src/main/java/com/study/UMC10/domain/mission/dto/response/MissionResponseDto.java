package com.study.UMC10.domain.mission.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.util.List;

public class MissionResponseDto {

    @Builder
    @Schema(description = "미션 목록")
    public record MissionListDto(
            @Schema(description = "조회된 미션 세부 정보 리스트")
            List<MissionDetailDto> missions
    ) {
    }

    @Builder
    @Schema(description = "미션 상세 정보")
    public record MissionDetailDto(
            @Schema(description = "미션 ID", example = "1")
            Long missionId,

            @Schema(description = "보상 포인트", example = "500")
            Integer rewardPoints,

            @Schema(description = "가게 이름", example = "반이마라 안암동")
            String storeName,

            @Schema(description = "미션 설명", example = "10000원 이상 식사")
            String description,

            @Schema(description = "미션 진행 상태", example = "IN_PROGRESS")
            String status
    ) {
    }

    @Builder
    @Schema(description = "미션 완료 처리 결과")
    public record MissionCompleteResultDto(
            @Schema(description = "완료 처리된 미션 ID", example = "1")
            Long missionId,

            @Schema(description = "변경된 미션 상태", example = "SUCCESS")
            String status
    ) {
    }

    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {}
}