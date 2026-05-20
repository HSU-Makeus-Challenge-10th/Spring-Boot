package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewResDTO {
    @Builder
    public record reviewDTO(
            Long reviewId,
            int score,
            String title
    ) {}

    @Builder
    public record WriteReviewResultDto(
            Long reviewId,
            String title,
            int score
    ) {}
    // 리뷰 조회
    @Builder
    public record getReview(
       Long reviewId,
       String title,
       String content
    ){}

    //페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext, // 다음 데이터가 있는지
            String nextCursor, // 다음 커서는 무엇인지
            Integer pageSize
    ){}

}
