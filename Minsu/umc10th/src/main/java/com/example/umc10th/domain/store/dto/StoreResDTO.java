package com.example.umc10th.domain.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public class StoreResDTO {

    @Builder
    public record StoreInfo(
            @JsonProperty("store_id") String storeId,
            String name,
            @JsonProperty("food_type") String foodType,
            @JsonProperty("detail_address") String detailAddress,
            @JsonProperty("avg_rating") Double avgRating,
            @JsonProperty("review_count") Long reviewCount,
            @JsonProperty("is_open") Boolean isOpen,
            @JsonProperty("img_url") String imgUrl,
            String lat,
            String lng
    ) {}

    @Builder
    public record HomeInfo(
            @JsonProperty("town_name") String townName,
            @JsonProperty("aram_count") Long aramCount,
            @JsonProperty("ongoing_mission_count") Long ongoingMissionCount
    ) {}
}
