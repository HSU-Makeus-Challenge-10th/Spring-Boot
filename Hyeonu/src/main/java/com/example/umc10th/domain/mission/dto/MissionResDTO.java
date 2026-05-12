package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {
    // 미션 목록
    @Builder
    public record MissionListResDTO(
        List<MissionDetailDTO> missionList,
        Integer listSize, // 데이터 몇개 불러오는지
        Integer totalPage, // 전체 페이지
        Long totalElements, // 전체 미션 개수
        Boolean isFirst, // 첫 페이지인지
        Boolean isLast // 마지막 페이지인지
    ){}
    // 미션 정보
    @Builder
    public record MissionDetailDTO(
            String storeName, // 가게 이름
            Integer rewardPoint, // 보상
            String content, // 미션 내용
            MissionStatus status // 미션 상태
    ){}

    @Builder
    public record RegionMissionListDTO(
            List<MissionDetailDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    // 가게 내 미션 조회
    @Builder
    public record GetMission(
            Long missionId,
            Integer rewardPoint,
            MissionStatus status,
            String content
    ){}

    // 페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}
}
