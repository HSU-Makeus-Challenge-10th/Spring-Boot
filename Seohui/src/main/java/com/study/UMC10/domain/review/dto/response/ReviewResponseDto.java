package com.study.UMC10.domain.review.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

public class ReviewResponseDto {

    @Builder
    @Schema(description = "리뷰 생성")
    public record CreateReviewResultDto(
            @Schema(description = "생성된 리뷰 ID", example = "1")
            Long reviewId,

            @Schema(description = "리뷰가 작성된 가게 ID", example = "15")
            Long storeId,

            @Schema(description = "작성한 별점", example = "4.5")
            Double rate,

            @Schema(description = "작성한 리뷰 내용", example = "음식이 맛있습니다!")
            String content,

            @Schema(description = "첨부된 리뷰 이미지 URL 목록", example = "[\"https://s3.url.com/image1.jpg\"]")
            List<String> images
    ) {
    }
}