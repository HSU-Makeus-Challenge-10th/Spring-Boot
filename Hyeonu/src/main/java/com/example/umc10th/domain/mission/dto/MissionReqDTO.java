package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


public class MissionReqDTO {
    // 미션 목록 조회
    @Builder
    public record MissionListReqDTO(
        MissionStatus status,
        Integer page
    ){}

    // 홈 화면 가능 미션 목록
    @Builder
    public record MyMissionReqDTO(
            Long regionId,
            Integer page
    ){}

    // 가게 미션 생성
    @Builder
    public record  CreateMission(
            @NotBlank(message="조건은 빈칸일 수 없습니다.")
            String content,
            @NotNull(message="마감기한은 필수입니다.")
            LocalDate deadline,
            @NotNull(message="미션 성공 포인트는 필수입니다.")
            Integer rewardPoint,
            MissionStatus status
    ){}
}
