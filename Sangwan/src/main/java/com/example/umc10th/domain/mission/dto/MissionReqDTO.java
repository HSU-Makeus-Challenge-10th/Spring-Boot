package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MissionReqDTO {

    public record CreateReviewReq(
            @NotNull Float rating,
            @NotBlank String content
    ) {}
}
