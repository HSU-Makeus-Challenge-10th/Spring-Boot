package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public class MissionReqDTO {

    // 가게 미션 생성
    public record CreateMission(
            @NotBlank(message = "미션 제목은 필수입니다.")
            String title,

            @NotNull(message = "마감기한은 필수입니다.")
            LocalDate deadline,

            @NotNull(message = "보상 포인트는 필수입니다.")
            @PositiveOrZero(message = "보상 포인트는 0 이상이어야 합니다.")
            Integer reward
    ) {}
}