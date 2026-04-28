package com.example.umc10th.domain.mission.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MissionReqDTO {

    public record SuccessRequest(
            @JsonProperty("approver_code") String approverCode
    ) {}
}
