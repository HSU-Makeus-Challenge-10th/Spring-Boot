package com.umc.study.domain.user.web.dto;

import java.util.List;

public record GetHomeRes(
        String userLocation,
        Integer currentPoint,
        Integer completedMissionCound,
        List<NotAcceptedMission> missionList
) {
    public record NotAcceptedMission(
            Long restaurantId,
            String restaurantName,
            String restaurantType,
            Integer priceLowerLimit,
            Integer rewardPoint
    ) {}
}
