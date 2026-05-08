package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.store.entity.Store;

public class ReviewConverter {

    // 사용자가 보낸 DTO를 DB에 저장할 엔티티로 (조회가 아닌 생성(작성)이라 추가됨)
    public static Review toReview(ReviewReqDTO.CreateReview request,Member member,Store store){
        return Review.builder()
                .rating(request.rating())
                .content(request.content())
                .member(member) // 연관관계 매핑
                .store(store) // 연관관계 매핑
                .build();
    }

    // 저장된 결과를 DTO로 포장
    public static ReviewResDTO.CreateReviewResult toCreateReviewResult(Review review) {
        return ReviewResDTO.CreateReviewResult.builder()
                .id(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}