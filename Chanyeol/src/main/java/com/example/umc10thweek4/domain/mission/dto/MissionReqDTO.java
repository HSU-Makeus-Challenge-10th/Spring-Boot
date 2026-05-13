package com.example.umc10thweek4.domain.mission.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class MissionReqDTO {

    public record CreateMission(

            @NotBlank(message = "미션 제목을 입력해주세요")
            String missionTitle,

            @NotBlank(message = "미션 설명을 입력해주세요")
            String missionDetail,

            @NotNull(message = "미션 마감일을 입력해주세요")
            LocalDateTime missionDeadline,

            @NotNull(message = "미션 보상을 입력해주세요")
            Long missionReward,

            @NotNull(message = "목표 금액을 입력해주세요")
            Integer targetAmount
    ) {}
}