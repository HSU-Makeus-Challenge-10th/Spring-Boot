package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.entity.MemberMissionStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    // 가게 내 미션 조회
    @Builder
    public record GetMission(
            Long id,
            Long storeId,
            String title,
            LocalDate deadline,
            Integer reward
    ) {}

    // 내가 진행 중인 미션 조회
    @Builder
    public record MyOngoingMission(
            Long memberMissionId,
            Long missionId,
            Long storeId,
            String title,
            LocalDate deadline,
            Integer reward,
            MemberMissionStatus status
    ) {}

    // 페이지네이션 툴
    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){}

}