package com.example.umc10th.domain.review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewInfo(
            @JsonProperty("review_id") String reviewId,
            @JsonProperty("store_id") String storeId,
            Double rating,
            String body,
            @JsonProperty("img_urls") List<String> imgUrls,
            @JsonProperty("created_at") LocalDateTime createdAt
    ) {}

    @Builder
    public record MyReviewItem(
            @JsonProperty("review_id") String reviewId,
            @JsonProperty("store_name") String storeName,
            Double rating,
            String body,
            @JsonProperty("img_urls") List<String> imgUrls,
            @JsonProperty("created_at") LocalDateTime createdAt
    ) {}

    @Builder
    public record MyReviewPageResult(
            List<MyReviewItem> reviews,
            String nextCursor,
            Boolean hasNext
    ) {}

    @Builder
    public record OwnerReply(String body) {}

    @Builder
    public record StoreReviewItem(
            @JsonProperty("review_id") String reviewId,
            @JsonProperty("reviewer_nickname") String reviewerNickname,
            Double rating,
            String body,
            @JsonProperty("img_urls") List<String> imgUrls,
            @JsonProperty("created_at") LocalDateTime createdAt,
            @JsonProperty("owner_reply") OwnerReply ownerReply
    ) {}

    @Builder
    public record StoreReviewPageResult(
            List<StoreReviewItem> reviews,
            String nextCursor,
            Boolean hasNext
    ) {}

    @Builder
    public record ReviewImageItem(
            @JsonProperty("review_img_id") String reviewImgId,
            @JsonProperty("img_url") String imgUrl,
            @JsonProperty("review_id") String reviewId,
            @JsonProperty("created_at") LocalDateTime createdAt
    ) {}

    @Builder
    public record ReviewImagePageResult(
            List<ReviewImageItem> images,
            String nextCursor,
            Boolean hasNext
    ) {}
}
