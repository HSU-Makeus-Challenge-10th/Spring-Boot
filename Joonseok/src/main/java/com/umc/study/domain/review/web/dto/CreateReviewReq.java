package com.umc.study.domain.review.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateReviewReq {

    // image는 제외

    @NotNull(message = "음식점 ID는 비어있을 수 없습니다.")
    private final Long restaurantId;

    // FE에서 1 ~ 2로 전달받음
    @NotNull(message = "리뷰 점수는 비어있을 수 없습니다.") @Min(1) @Max(10)
    private final Double score;

    @NotBlank(message = "리뷰 내용은 비어있을 수 없습니다.")
    private String content;

    public CreateReviewReq(Long restaurantId, int score, String content) {
        this.restaurantId = restaurantId;
        this.score = (double) score/2;
        this.content = content;
    }
}
