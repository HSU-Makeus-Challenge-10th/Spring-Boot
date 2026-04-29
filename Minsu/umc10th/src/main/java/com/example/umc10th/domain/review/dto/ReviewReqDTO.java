package com.example.umc10th.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ReviewReqDTO {

    public record CreateReview(
            @Schema(description = "별점 (1.0 ~ 5.0)", example = "4.5")
            Double rating,
            @Schema(description = "리뷰 내용", example = "음식이 정말 맛있고 양도 많아요!")
            String body
    ) {}
}
