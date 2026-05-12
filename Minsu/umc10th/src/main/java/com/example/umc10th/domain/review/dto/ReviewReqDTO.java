package com.example.umc10th.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReviewReqDTO {

    public record CreateReview(
            @Schema(description = "별점 (1.0 ~ 5.0)", example = "4.5")
            @NotNull(message = "별점은 필수입니다.")
            @DecimalMin(value = "1.0", message = "별점은 1.0 이상이어야 합니다.")
            @DecimalMax(value = "5.0", message = "별점은 5.0 이하여야 합니다.")
            Double rating,
            @Schema(description = "리뷰 내용", example = "음식이 정말 맛있고 양도 많아요!")
            @NotBlank(message = "리뷰 내용은 필수입니다.")
            @Size(max = 500, message = "리뷰 내용은 500자 이내여야 합니다.")
            String body
    ) {}

    public record Update(
            @Schema(description = "별점 (1.0 ~ 5.0)", example = "4.0")
            @DecimalMin(value = "1.0", message = "별점은 1.0 이상이어야 합니다.")
            @DecimalMax(value = "5.0", message = "별점은 5.0 이하여야 합니다.")
            Double rating,
            @Schema(description = "리뷰 내용", example = "수정된 리뷰 내용입니다.")
            @Size(max = 500, message = "리뷰 내용은 500자 이내여야 합니다.")
            String body
    ) {}
}
