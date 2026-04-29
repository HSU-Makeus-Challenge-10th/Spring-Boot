package com.umc.study.domain.mission.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionComplReq {

    @NotNull(message = "missionLogId 필드는 비어있을 수 없습니다.")
    private final Long missionLogId;
}
