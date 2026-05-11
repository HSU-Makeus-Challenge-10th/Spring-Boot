package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;

public class MissionResDTO {

    @Builder
    public record MissionInfo(
            Long id,
            Long storeId,
            String title,
            LocalDate deadline,
            Integer reward
    ) {
    }
}