package com.study.UMC10.domain.review.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewRequestDto {

    @Schema(description = "리뷰 생성")
    public record CreateReviewDto(

            @NotNull(message = "별점은 필수 입력 항목입니다.")
            @DecimalMin(value = "0.0", message = "별점은 0.0 이상이어야 합니다.")
            @DecimalMax(value = "5.0", message = "별점은 5.0 이하이어야 합니다.")
            @Schema(description = "가게 별점", example = "4.5")
            Double rate,

            @NotBlank(message = "리뷰 내용은 비어있을 수 없습니다.")
            @Schema(description = "리뷰 내용", example = "음식이 맛있어요!")
            String content
    ) {
    }
}