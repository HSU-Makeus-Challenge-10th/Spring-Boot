package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MissionResDTO {
    @Builder
    public record MissionChallengeResult(
            Long memberMissionId,
            LocalDateTime createdAt
    ){}

    @Builder
    public record MissionSummaryDTO(
            Long missionId,
            String storeName,    // 어떤 가게의 미션인지
            Integer rewardPoint, // 보상 포인트
            String content,      // 미션 내용
            String status        // 현재 상태 (CHALLENGING 등)
    ){}
}
