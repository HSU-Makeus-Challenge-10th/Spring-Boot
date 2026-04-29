package com.umc.study.domain.user.web.dto;

import java.util.List;

public record GetComplMissionRes(
        List<CompletedMission> missionList
) {
    public record CompletedMission(
            Long restaurantId,
            String restaurantName,
            Integer priceLowerLimit,
            Boolean isCompleted
    ) {}
}
