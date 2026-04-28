package com.example.umc10th.domain.mission.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record ActivatedMissionInfo(
            @JsonProperty("activated_mission_id")
            @Schema(description = "진행 미션 ID", example = "5")
            String activatedMissionId,
            @JsonProperty("mission_id")
            @Schema(description = "미션 ID", example = "1")
            String missionId,
            @Schema(description = "미션 상태", example = "진행중")
            String state,
            @JsonProperty("started_at")
            @Schema(description = "시작 시각", example = "2022-05-22T16:43:00")
            LocalDateTime startedAt
    ) {}

    @Builder
    public record MissionPageResult(
            List<MissionItem> missions,
            @Schema(description = "다음 페이지 커서", example = "10")
            String nextCursor,
            @Schema(description = "다음 페이지 존재 여부", example = "true")
            Boolean hasNext
    ) {}

    @Builder
    public record MissionItem(
            @JsonProperty("activated_mission_id")
            @Schema(description = "진행 미션 ID", example = "5")
            String activatedMissionId,
            @JsonProperty("mission_id")
            @Schema(description = "미션 ID", example = "1")
            String missionId,
            @JsonProperty("store_name")
            @Schema(description = "가게 이름", example = "반이학생마라탕")
            String storeName,
            @JsonProperty("min_payment_amount")
            @Schema(description = "최소 주문 금액", example = "12000")
            Integer minPaymentAmount,
            @JsonProperty("reward_points")
            @Schema(description = "적립 포인트", example = "500")
            Integer rewardPoints,
            @Schema(description = "미션 상태", example = "진행중")
            String state,
            @Schema(description = "마감일", example = "2022-05-29")
            LocalDate deadline
    ) {}

    @Builder
    public record AvailableMissionPageResult(
            List<AvailableMissionItem> missions,
            @Schema(description = "다음 페이지 커서", example = "10")
            String nextCursor,
            @Schema(description = "다음 페이지 존재 여부", example = "false")
            Boolean hasNext
    ) {}

    @Builder
    public record AvailableMissionItem(
            @JsonProperty("mission_id")
            @Schema(description = "미션 ID", example = "1")
            String missionId,
            @JsonProperty("store_name")
            @Schema(description = "가게 이름", example = "반이학생마라탕")
            String storeName,
            @JsonProperty("store_id")
            @Schema(description = "가게 ID", example = "1")
            String storeId,
            @JsonProperty("food_type")
            @Schema(description = "음식 종류", example = "중식당")
            String foodType,
            @JsonProperty("min_payment_amount")
            @Schema(description = "최소 주문 금액", example = "10000")
            Integer minPaymentAmount,
            @JsonProperty("reward_points")
            @Schema(description = "적립 포인트", example = "500")
            Integer rewardPoints,
            @Schema(description = "마감일", example = "2022-05-29")
            LocalDate deadline,
            @JsonProperty("d_day")
            @Schema(description = "마감 D-Day", example = "7")
            Long dDay,
            @JsonProperty("distance_km")
            @Schema(description = "거리(km)", example = "1.0")
            Double distanceKm
    ) {}

    @Builder
    public record ApproverCodeInfo(
            @JsonProperty("approver_code")
            @Schema(description = "미션 구분번호", example = "132754")
            String approverCode
    ) {}

    @Builder
    public record StoreMissionPageResult(
            List<StoreMissionItem> missions,
            @Schema(description = "다음 페이지 커서", example = "10")
            String nextCursor,
            @Schema(description = "다음 페이지 존재 여부", example = "false")
            Boolean hasNext
    ) {}

    @Builder
    public record StoreMissionItem(
            @JsonProperty("mission_id")
            @Schema(description = "미션 ID", example = "1")
            String missionId,
            @Schema(description = "미션 설명", example = "12,000원 이상 주문 시 500P 적립")
            String description,
            @JsonProperty("min_payment_amount")
            @Schema(description = "최소 주문 금액", example = "12000")
            Integer minPaymentAmount,
            @JsonProperty("reward_points")
            @Schema(description = "적립 포인트", example = "500")
            Integer rewardPoints,
            @Schema(description = "마감일", example = "2022-05-29")
            LocalDate deadline
    ) {}
}
