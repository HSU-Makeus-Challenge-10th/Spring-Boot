package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    // 가게 내 미션 조회
    @Builder
    public record MissionInfo(
            Long id,
            Long storeId,
            String title,
            LocalDate deadline,
            Integer reward
    ) {}

    // 페이지네이션 툴
    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){}
}