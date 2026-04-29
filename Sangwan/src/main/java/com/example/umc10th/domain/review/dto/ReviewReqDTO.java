package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    public record CreateReviewReq(
            @NotNull Float rating,
            @NotBlank String content
    ) {}
}
