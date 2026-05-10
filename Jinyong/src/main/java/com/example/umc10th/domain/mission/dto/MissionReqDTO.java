package com.example.umc10th.domain.mission.dto;

import java.time.LocalDate;

public class MissionReqDTO {

    // 가게 미션 생성
    public record CreateMission(
            String title,
            LocalDate deadline,
            Integer reward
    ) {}
}
