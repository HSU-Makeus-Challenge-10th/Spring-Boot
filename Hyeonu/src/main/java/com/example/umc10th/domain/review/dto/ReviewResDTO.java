package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReviewResDTO {
    @Builder
    public record CreateReviewResult(
            Long reviewId,
            LocalDateTime createdAt
    ){}

    @Builder
    public record ReviewDetail(
            Long reviewId,
            String writerName,
            Float rating,
            String content,
            LocalDate createdAt
    ){}
}
