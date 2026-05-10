package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


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
}
