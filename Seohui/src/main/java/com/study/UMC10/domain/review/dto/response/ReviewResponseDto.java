package com.study.UMC10.domain.review.dto.response;

import lombok.Builder;

import java.util.List;

public class ReviewResponseDto {

    @Builder
    public record CreateReviewResultDto(
            Long reviewId,
            Long storeId,
            Double rate,
            String content,
            List<String> images
    ) {
    }
}
