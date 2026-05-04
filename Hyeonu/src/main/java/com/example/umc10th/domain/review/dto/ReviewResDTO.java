package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResDTO {
    // 리뷰를 보는 시점
    @Builder
    public record PostReivewResultDTO(
            Long reviewId, // 저장된 리뷰의 고유 ID
            LocalDateTime createdAt // 서버에서 생성된 시간
    ){}
}
