package com.example.umc10thweek4.domain.review.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    public record Create(
            Long reviewId,
            Double reviewRate,
            LocalDateTime createdAt
    ) {}

    public record GetReviewList(
            Long reviewId,
            String content,
            Double rating,
            LocalDateTime reviewDate,
            LocalDateTime createdAt
    ) {}

    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {}
}