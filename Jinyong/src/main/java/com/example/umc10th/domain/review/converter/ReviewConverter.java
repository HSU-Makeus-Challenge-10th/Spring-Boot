package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

public class ReviewConverter {

    public static ReviewResDTO.ReviewInfo toReviewInfo(Review review) {
        return ReviewResDTO.ReviewInfo.builder()
                .id(review.getId())
                .userId(review.getMember().getId())
                .storeId(review.getStore().getId())
                .userMissionId(review.getMemberMission().getId())
                .content(review.getContent())
                .score(review.getScore())
                .build();
    }
}