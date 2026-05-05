package com.umc.study.domain.user.web.dto;

import java.util.List;

public record GetHomeRes(
        Integer curPage,
        Boolean hasNext,
        String userLocation,
        Integer currentPoint,
        Integer completedMissionCount,
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
