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
            @Schema(description = "가게 이름", example = "요아정")
            String storeName, // 가게 이름
            @Schema(description = "미션 보상 포인트", example = "500")
            Integer reward, // 보상
            @Schema(description = "미션 내용", example = "요거트 아이스크림 1개 먹기")
            String missionSpec, // 미션 내용
            @Schema(description = "미션 상태", example = "CHALLENGING")
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
}
