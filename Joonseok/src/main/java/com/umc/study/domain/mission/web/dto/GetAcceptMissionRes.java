package com.umc.study.domain.mission.web.dto;

import java.util.List;

public record GetAcceptMissionRes(
        Integer curPage,
        Boolean hasNext,
        List<GetAcceptMissionRes.InProgressMission> inProgress
) {

    public record InProgressMission(
            Long missionLogId,
            Integer mileage,
            Long restaurantId,
            String restaurantName,
            Integer priceLowerLimit
    ) {}
}
