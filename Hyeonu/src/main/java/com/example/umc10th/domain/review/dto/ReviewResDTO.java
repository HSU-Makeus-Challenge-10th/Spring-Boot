package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {
    @Builder
    public record CreateReviewResult(
            Long id,
            LocalDateTime createdAt
    ){}

    // 전체 목록 응답
    @Builder
    public record MyReviewDetailDTO(
            Long reviewId,
            String storeName,
            String memberName,
            Float rating,
            String content,
            LocalDate createdAt
    ){}

    // 페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}
}
