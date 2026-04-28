package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {

    public record CreateReview(
            Double rating,
            String body
    ) {}
}
