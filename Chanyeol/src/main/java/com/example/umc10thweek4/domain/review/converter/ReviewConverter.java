package com.example.umc10thweek4.domain.review.converter;

import com.example.umc10thweek4.domain.review.dto.ReviewResDTO;
import com.example.umc10thweek4.domain.review.entity.Review;

import java.util.List;

public class ReviewConverter {

    public static ReviewResDTO.Create toCreateRes(Review review) {
        return new ReviewResDTO.Create(
                review.getId(),
                (double) review.getRating(),
                review.getReviewDate()
        );
    }

    public static ReviewResDTO.GetReviewList toGetListRes(Review review) {
        return new ReviewResDTO.GetReviewList(
                review.getId(),
                review.getContent(),
                (double) review.getRating(),
                review.getReviewDate(),
                review.getCreatedAt()
        );
    }

    public static <T> ReviewResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
        return new ReviewResDTO.Pagination<>(
                data,
                hasNext,
                nextCursor,
                pageSize
        );
    }
}