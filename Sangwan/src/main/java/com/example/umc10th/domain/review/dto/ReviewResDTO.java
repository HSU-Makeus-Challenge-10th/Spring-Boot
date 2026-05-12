package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResDTO {

    @Builder
    public record CreateReviewRes(
            Long reviewId,
            Long missionId,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record ReviewItem(
            Long reviewId,
            String storeName,
            Double rating,
            String content,
            LocalDateTime createdAt
    ) {}
}
