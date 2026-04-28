package com.example.umc10th.domain.review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewInfo(
            @JsonProperty("review_id")
            @Schema(description = "리뷰 ID", example = "10")
            String reviewId,
            @JsonProperty("store_id")
            @Schema(description = "가게 ID", example = "1")
            String storeId,
            @Schema(description = "별점", example = "4.5")
            Double rating,
            @Schema(description = "리뷰 내용", example = "너무 맛있어요! 다음에 또 올게요.")
            String body,
            @JsonProperty("img_urls")
            @Schema(description = "이미지 URL 목록", example = "[\"https://example.com/img1.jpg\"]")
            List<String> imgUrls,
            @JsonProperty("created_at")
            @Schema(description = "작성 시각", example = "2022-05-22T16:43:00")
            LocalDateTime createdAt
    ) {}

    @Builder
    public record MyReviewItem(
            @JsonProperty("review_id")
            @Schema(description = "리뷰 ID", example = "10")
            String reviewId,
            @JsonProperty("store_name")
            @Schema(description = "가게 이름", example = "반이학생마라탕")
            String storeName,
            @Schema(description = "별점", example = "4.5")
            Double rating,
            @Schema(description = "리뷰 내용", example = "너무 맛있어요! 다음에 또 올게요.")
            String body,
            @JsonProperty("img_urls")
            @Schema(description = "이미지 URL 목록", example = "[\"https://example.com/img1.jpg\"]")
            List<String> imgUrls,
            @JsonProperty("created_at")
            @Schema(description = "작성 시각", example = "2022-05-14T10:00:00")
            LocalDateTime createdAt
    ) {}

    @Builder
    public record MyReviewPageResult(
            List<MyReviewItem> reviews,
            @Schema(description = "다음 페이지 커서", example = "10")
            String nextCursor,
            @Schema(description = "다음 페이지 존재 여부", example = "true")
            Boolean hasNext
    ) {}

    @Builder
    public record OwnerReply(
            @Schema(description = "사장님 답글", example = "감사합니다. 다음에 또 방문해 주세요!")
            String body
    ) {}

    @Builder
    public record StoreReviewItem(
            @JsonProperty("review_id")
            @Schema(description = "리뷰 ID", example = "1")
            String reviewId,
            @JsonProperty("reviewer_nickname")
            @Schema(description = "작성자 닉네임", example = "닉네임1234")
            String reviewerNickname,
            @Schema(description = "별점", example = "4.5")
            Double rating,
            @Schema(description = "리뷰 내용", example = "너무 맛있어요...")
            String body,
            @JsonProperty("img_urls")
            @Schema(description = "이미지 URL 목록", example = "[\"https://example.com/img1.jpg\"]")
            List<String> imgUrls,
            @JsonProperty("created_at")
            @Schema(description = "작성 시각", example = "2022-05-14T10:00:00")
            LocalDateTime createdAt,
            @JsonProperty("owner_reply")
            OwnerReply ownerReply
    ) {}

    @Builder
    public record StoreReviewPageResult(
            List<StoreReviewItem> reviews,
            @Schema(description = "다음 페이지 커서", example = "10")
            String nextCursor,
            @Schema(description = "다음 페이지 존재 여부", example = "false")
            Boolean hasNext
    ) {}

    @Builder
    public record ReviewImageItem(
            @JsonProperty("review_img_id")
            @Schema(description = "리뷰 이미지 ID", example = "1")
            String reviewImgId,
            @JsonProperty("img_url")
            @Schema(description = "이미지 URL", example = "https://example.com/img1.jpg")
            String imgUrl,
            @JsonProperty("review_id")
            @Schema(description = "리뷰 ID", example = "5")
            String reviewId,
            @JsonProperty("created_at")
            @Schema(description = "업로드 시각", example = "2022-05-14T10:00:00")
            LocalDateTime createdAt
    ) {}

    @Builder
    public record ReviewImagePageResult(
            List<ReviewImageItem> images,
            @Schema(description = "다음 페이지 커서", example = "10")
            String nextCursor,
            @Schema(description = "다음 페이지 존재 여부", example = "false")
            Boolean hasNext
    ) {}
}
