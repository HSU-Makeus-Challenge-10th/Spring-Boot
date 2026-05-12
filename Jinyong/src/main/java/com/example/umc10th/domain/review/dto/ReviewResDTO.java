package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.util.List;

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

    // 내가 작성한 리뷰 조회
    @Builder
    public record MyReview(
            Long reviewId,
            Long userId,
            Long storeId,
            Long userMissionId,
            String content,
            Integer score
    ) {}

    // 커서 기반 페이지네이션
    @Builder
    public record CursorPagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {}
}