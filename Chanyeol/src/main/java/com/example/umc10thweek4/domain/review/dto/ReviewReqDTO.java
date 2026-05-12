package com.example.umc10thweek4.domain.review.dto;

import jakarta.validation.constraints.*;

import java.util.List;

public class ReviewReqDTO {

    public record Create(
            @NotNull(message = "별점을 입력해주세요")
            @DecimalMin(value = "1.0", message = "별점은 최소 1.0 이상이어야 합니다")
            @DecimalMax(value = "5.0", message = "별점은 최대 5.0 이하여야 합니다")
            Double reviewRate,

            @NotBlank(message = "리뷰 내용을 입력해주세요")
            String reviewComment,

            @Size(max = 5, message = "이미지는 최대 5개까지만 등록할 수 있습니다")
            List<String> imageUrls
    ) {}

    public enum SortType {
        ID,     // 최신순
        RATING  // 별점 높은 순
    }
}
