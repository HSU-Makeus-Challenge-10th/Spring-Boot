package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.store.entity.Store;

public class ReviewConverter {
    public static Review toReview(ReviewReqDTO.CreateReview request, Member member, Store store) {
        return Review.builder()
                .rating(request.rating())
                .content(request.content())
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewResDTO.CreateReviewResult toCreateReviewResult(Review review) {
        return ReviewResDTO.CreateReviewResult.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.ReviewDetail toReviewSummaryDTO(Review review) {
        return ReviewResDTO.ReviewDetail.builder()
                .reviewId(review.getId())
                .writerName(review.getMember().getName()) // 엔티티 그래프에서 이름만 추출
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate()) // 날짜 형식 정제
                .build();
    }
}