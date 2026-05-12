package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionState;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MissionReqDTO {

    public record GetOngoingMissions(
            @NotNull(message = "회원 ID는 필수입니다.")
            Long memberId,

            @NotNull(message = "미션 상태는 필수입니다.")
            MissionState state,

            @NotNull(message = "페이지 번호는 필수입니다.")
            @Min(value = 0, message = "페이지 번호는 0 이상이어야 합니다.")
            Integer page,

            @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
            Integer size
    ) {
    }

    public record SuccessRequest(
            @JsonProperty("approver_code")
            @Schema(description = "미션 승인 구분번호", example = "920394")
            String approverCode
    ) {}
}
