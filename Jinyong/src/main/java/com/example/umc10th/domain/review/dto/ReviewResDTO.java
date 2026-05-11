package com.example.umc10th.domain.review.dto;

import lombok.Builder;

public class ReviewResDTO {

    @Builder
    public record ReviewInfo(
            Long id,
            Long userId,
            Long storeId,
            Long userMissionId,
            String content,
            Integer score
    ) {}
}