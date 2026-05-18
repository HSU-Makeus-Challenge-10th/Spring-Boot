package com.example.umc10thweek4.domain.mission.dto;

import com.example.umc10thweek4.domain.mission.enums.MissionStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    public record Home(
            String region,
            Integer totalPoint,
            Integer missionCount,
            Integer goalCount,
            Integer goalReward,
            List<RecommendedMission> recommendedMissions,
            boolean unreadNotice
    ) {
        public record RecommendedMission(
                Long missionId,
                String store,
                String category,
                Integer missionReward,
                String missionDeadline,
                Integer targetAmount
        ) {}
    }

    public record UserMissionList(
            List<UserMission> missions
    ) {
        public record UserMission(
                Long userMissionId,
                Long missionId,
                String title,
                String store,
                Integer recognizeAmount,
                MissionStatus status,
                Integer missionReward
        ) {}
    }

    public record Complete(
            MissionStatus status,
            String title,
            String text,
            Integer missionReward,
            LocalDateTime completeTime
    ) {}

    public record GetMission(
            Long missionId,
            String missionTitle,
            String missionDetail,
            Long missionReward,
            Integer targetAmount,
            LocalDateTime missionDeadline
    ) {}

    public record Participate(
            Long userMissionId,
            Long missionId,
            MissionStatus status
    ) {}

    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {}
}