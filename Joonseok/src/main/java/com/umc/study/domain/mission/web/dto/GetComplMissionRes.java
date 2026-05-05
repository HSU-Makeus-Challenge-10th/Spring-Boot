package com.umc.study.domain.mission.web.dto;

import java.util.List;

public record GetMissionRes(
        List<CompletedMission> completed,
        List<InProgressMission> inProgress
) {

    public record CompletedMission(
            Long missionLogId,
            Integer point,
            Long restaurantId,
            String restaurantName,
            Integer priceLowerLimit
    ) {}

    public record InProgressMission(
            Long missionLogId,
            Integer mileage,
            Long restaurantId,
            String restaurantName,
            Integer priceLowerLimit
    ) {}
}
