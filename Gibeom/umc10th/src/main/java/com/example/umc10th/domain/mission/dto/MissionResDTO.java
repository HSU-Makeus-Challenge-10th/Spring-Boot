package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.member.enums.MissionStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionDto(
            Long missionId,
            String title,
            Integer rewardPoint
    ) {}

    //가게 내 미션 조회
    @Builder
    public record GetMission(
            Long missionId,
            Integer point,
            String conditional
    ){}

    //페이지네이션 틀
    @Builder
    public record Pagination<T>(
        List<T> data,
        Integer pageNumber,
        Integer pageSize
    ){}

    //생성된 미션 정보 반환 DTO
    @Builder
    public record CreateMissionResult(
            Long missionId,
            String conditional,
            Integer point,
            LocalDate deadline
    ){}
}
