package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.MemberMission;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.WriteReviewDto dto, Member member, MemberMission memberMission) {
        return Review.builder()
                .member(member)
                .memberMission(memberMission)
                .score(dto.score())
                .title(dto.title())
                .content(dto.content())
                .build();
    }

    public static ReviewResDTO.WriteReviewResultDto toWriteReviewResult(Review review) {
        return ReviewResDTO.WriteReviewResultDto.builder()
                .reviewId(review.getId())
                .title(review.getTitle())
                .score(review.getScore())
                .build();
    }

    public static ReviewResDTO.reviewDTO toReviewDTO(Review review) {
        return ReviewResDTO.reviewDTO.builder()
                .reviewId(review.getId())
                .score(review.getScore())
                .title(review.getTitle())
                .build();
    }
    public static List<ReviewResDTO.reviewDTO> toReviewDTOList(List<Review> reviews) {
        return reviews.stream()
                .map(ReviewConverter::toReviewDTO)
                .collect(Collectors.toList());
    }

    //
    public static ReviewResDTO.getReview toGetReview(
            Review review
    ) {
        return ReviewResDTO.getReview.builder()
                .reviewId(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .build();
    }

    //페이지네이션 틀 생성
    public static <T> ReviewResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize

    ) {
        return ReviewResDTO.Pagination.<T>builder()
                .data(data)
                .pageSize(pageSize)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .build();
    }
}
