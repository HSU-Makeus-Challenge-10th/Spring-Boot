package com.umc.study.domain.mission.web.dto;

import java.util.List;

public record GetComplMissionRes(
        Integer curPage,
        Boolean hasNext,
        List<GetComplMissionRes.CompletedMission> completed
) {

    public record CompletedMission(
            Long missionLogId,
            Integer point,
            Long restaurantId,
            String restaurantName,
            Integer priceLowerLimit
    ) {}

}
