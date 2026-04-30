package com.example.umc10thweek4.domain.review.dto;

public class ReviewReqDTO {

    public record Create(
            Double reviewRate,
            String reviewComment,
            String reviewImage
    ) {}
}
