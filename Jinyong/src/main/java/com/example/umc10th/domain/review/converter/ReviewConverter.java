package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

import java.util.List;

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

    public static ReviewResDTO.MyReview toMyReview(Review review) {
        return ReviewResDTO.MyReview.builder()
                .reviewId(review.getId())
                .userId(review.getMember().getId())
                .storeId(review.getStore().getId())
                .userMissionId(review.getMemberMission().getId())
                .content(review.getContent())
                .score(review.getScore())
                .build();
    }

    public static <T> ReviewResDTO.CursorPagination<T> toCursorPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
        return ReviewResDTO.CursorPagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}