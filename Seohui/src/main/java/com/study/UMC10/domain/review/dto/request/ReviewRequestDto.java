package com.study.UMC10.domain.review.dto.request;

public class ReviewRequestDto {

    public record CreateReviewDto(
            Double rate,
            String content
    ) {
    }
}
