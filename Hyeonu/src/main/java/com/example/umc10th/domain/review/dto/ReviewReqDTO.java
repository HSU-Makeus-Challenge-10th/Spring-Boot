package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewReqDTO {
    @Builder
    public record CreateReview(
            Long memberId,
            Float rating,
            String content
    ){}
}