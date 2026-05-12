package com.example.umc10th.domain.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResDTO {

    @Builder
    public record SignupRes(
            Long memberId,
            String name,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record HomeRes(
            Integer memberPoint,
            String regionName,
            MissionProgress missionProgress,
            List<HomeMissionItem> missions
    ) {}

    @Builder
    public record MissionProgress(
            Integer currentCount,
            Integer targetCount,
            Integer rewardPoint
    ) {}

    @Builder
    public record HomeMissionItem(
            Long missionId,
            String storeName,
            String category,
            String content,
            Integer reward,
            Integer dDay
    ) {}

    @Builder
    public record MissionItem(
            Long memberMissionId,
            Long missionId,
            String storeName,
            String content,
            String reward,
            String status,
            String rewardType
    ) {}

    @Builder
    public record MissionSuccessRes(
            Long memberMissionId,
            String status,
            LocalDateTime updatedAt
    ) {}

    @Builder
    public record MyInfoRes(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ){}

}
