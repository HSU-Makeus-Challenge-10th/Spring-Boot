package com.study.UMC10.domain.review.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class ReviewRequestDto {

    @Schema(description = "리뷰 생성")
    public record CreateReviewDto(
            @Schema(description = "가게 별점", example = "4.5")
            Double rate,

            @Schema(description = "리뷰 내용", example = "음식이 맛있습니다!")
            String content
    ) {
    }
}