package com.example.umc10th.domain.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public class StoreResDTO {

    @Builder
    public record StoreInfo(
            @JsonProperty("store_id")
            @Schema(description = "가게 ID", example = "1")
            String storeId,
            @Schema(description = "가게 이름", example = "반이학생마라탕")
            String name,
            @JsonProperty("food_type")
            @Schema(description = "음식 종류", example = "중식당")
            String foodType,
            @JsonProperty("detail_address")
            @Schema(description = "상세 주소", example = "서울시 성북구 안암동5가 102-60")
            String detailAddress,
            @JsonProperty("avg_rating")
            @Schema(description = "평균 별점", example = "4.4")
            Double avgRating,
            @JsonProperty("review_count")
            @Schema(description = "리뷰 수", example = "128")
            Long reviewCount,
            @JsonProperty("is_open")
            @Schema(description = "영업 여부", example = "true")
            Boolean isOpen,
            @JsonProperty("img_url")
            @Schema(description = "대표 이미지 URL", example = "https://example.com/store.jpg")
            String imgUrl,
            @Schema(description = "위도", example = "37.5894")
            String lat,
            @Schema(description = "경도", example = "127.0320")
            String lng
    ) {}

    @Builder
    public record HomeInfo(
            @JsonProperty("town_name")
            @Schema(description = "동네 이름", example = "안암동")
            String townName,
            @JsonProperty("aram_count")
            @Schema(description = "아람 수 (누적 참여자 수)", example = "999999")
            Long aramCount,
            @JsonProperty("ongoing_mission_count")
            @Schema(description = "진행중인 미션 수", example = "7")
            Long ongoingMissionCount
    ) {}
}
