package com.example.umc10thweek4.domain.review.dto;

import java.time.LocalDateTime;

public class ReviewResDTO {

    public record Create(
            Long reviewId,
            Double reviewRate,
            LocalDateTime createdAt
    ) {}
}