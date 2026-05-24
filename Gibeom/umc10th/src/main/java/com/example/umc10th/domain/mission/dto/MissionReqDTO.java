package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.member.enums.MissionStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MissionReqDTO {
    //가게 미션 생성
    public record CreateMission(
            @NotNull(message = "마감기한은 필수입니다.")
            LocalDate deadLine,
            @NotNull(message = "미션 성공 포인트는 필수입니다.")
            Integer point,
            @NotBlank(message = "조건은 빈칸일 수 없습니다.")
            String conditional
    ){}
}
