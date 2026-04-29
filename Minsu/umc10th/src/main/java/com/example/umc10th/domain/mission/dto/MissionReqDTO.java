package com.example.umc10th.domain.mission.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class MissionReqDTO {

    public record SuccessRequest(
            @JsonProperty("approver_code")
            @Schema(description = "미션 승인 구분번호", example = "920394")
            String approverCode
    ) {}
}
