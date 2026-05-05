package com.example.umc10th.domain.review.dto;

import lombok.Builder;

public class ReviewResDTO {
    @Builder
    public record reviewDTO(
            Long reviewId,
            int score,
            String title
    ) {}

    @Builder
    public record WriteReviewResultDto(
            Long reviewId,
            String title,
            int score
    ) {}
}
