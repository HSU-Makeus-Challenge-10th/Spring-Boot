package com.example.umc10th.domain.review.dto;

import java.util.List;

public class ReviewReqDTO {
    public record CreateReview(
            Long memberId,
            Float rating,
            String content,
            List<String> imageUrls
    ){}
}