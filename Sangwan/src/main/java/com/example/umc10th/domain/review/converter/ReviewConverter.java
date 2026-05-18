package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.global.dto.CursorPageRes;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResDTO.ReviewItem toReviewItem(Review review) {
        return ReviewResDTO.ReviewItem.builder()
                .reviewId(review.getId())
                .storeName(review.getMemberMission().getMission().getStore().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static CursorPageRes<ReviewResDTO.ReviewItem> toReviewListRes(
            List<Review> reviews, boolean hasNext,
            Long nextCursor, Double nextRatingCursor,
            boolean isFirst, boolean isLast) {
        List<ReviewResDTO.ReviewItem> items = reviews.stream()
                .map(ReviewConverter::toReviewItem)
                .collect(Collectors.toList());

        return CursorPageRes.<ReviewResDTO.ReviewItem>builder()
                .list(items)
                .listSize(items.size())
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .nextRatingCursor(nextRatingCursor)
                .isFirst(isFirst)
                .isLast(isLast)
                .build();
    }
}
