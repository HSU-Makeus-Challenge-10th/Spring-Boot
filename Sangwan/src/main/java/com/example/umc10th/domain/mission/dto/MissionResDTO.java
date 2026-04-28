package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MissionResDTO {

    @Builder
    public record CreateReviewRes(
            Long reviewId,
            Long missionId,
            LocalDateTime createdAt
    ) {}
}
