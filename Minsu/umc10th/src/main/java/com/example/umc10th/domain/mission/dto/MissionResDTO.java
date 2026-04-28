package com.example.umc10th.domain.mission.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record ActivatedMissionInfo(
            @JsonProperty("activated_mission_id") String activatedMissionId,
            @JsonProperty("mission_id") String missionId,
            String state,
            @JsonProperty("started_at") LocalDateTime startedAt
    ) {}

    @Builder
    public record MissionPageResult(
            List<MissionItem> missions,
            String nextCursor,
            Boolean hasNext
    ) {}

    @Builder
    public record MissionItem(
            @JsonProperty("activated_mission_id") String activatedMissionId,
            @JsonProperty("mission_id") String missionId,
            @JsonProperty("store_name") String storeName,
            @JsonProperty("min_payment_amount") Integer minPaymentAmount,
            @JsonProperty("reward_points") Integer rewardPoints,
            String state,
            LocalDate deadline
    ) {}

    @Builder
    public record AvailableMissionPageResult(
            List<AvailableMissionItem> missions,
            String nextCursor,
            Boolean hasNext
    ) {}

    @Builder
    public record AvailableMissionItem(
            @JsonProperty("mission_id") String missionId,
            @JsonProperty("store_name") String storeName,
            @JsonProperty("store_id") String storeId,
            @JsonProperty("food_type") String foodType,
            @JsonProperty("min_payment_amount") Integer minPaymentAmount,
            @JsonProperty("reward_points") Integer rewardPoints,
            LocalDate deadline,
            @JsonProperty("d_day") Long dDay,
            @JsonProperty("distance_km") Double distanceKm
    ) {}

    @Builder
    public record ApproverCodeInfo(
            @JsonProperty("approver_code") String approverCode
    ) {}

    @Builder
    public record StoreMissionPageResult(
            List<StoreMissionItem> missions,
            String nextCursor,
            Boolean hasNext
    ) {}

    @Builder
    public record StoreMissionItem(
            @JsonProperty("mission_id") String missionId,
            String description,
            @JsonProperty("min_payment_amount") Integer minPaymentAmount,
            @JsonProperty("reward_points") Integer rewardPoints,
            LocalDate deadline
    ) {}
}
