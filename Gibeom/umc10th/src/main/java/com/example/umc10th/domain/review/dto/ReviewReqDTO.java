package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {
    public record WriteReviewDto(
            int score,
            String title,
            String content
    ) {}
}
