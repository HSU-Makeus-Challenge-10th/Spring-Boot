package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    //id 페이지네이션
    private ReviewResDTO.Pagination<ReviewResDTO.getReview> getMemberReviewsOrderById(
            Long memberId,
            Integer pageSize,
            String cursor
    ) {
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        Slice<Review> reviewList;
        String nextCursor;

        if (!cursor.equals("-1")) {
            long idCursor = Long.parseLong(cursor.split(":")[1]);
            reviewList = reviewRepository.findReviewsByMember_IdAndIdLessThanOrderByIdDesc(
                    memberId,
                    idCursor,
                    pageRequest
            );
        } else {
            reviewList = reviewRepository.findReviewsByMember_IdOrderByIdDesc(memberId, pageRequest);
        }

        List<Review> content = reviewList.getContent();
        nextCursor = (reviewList.hasNext() && !content.isEmpty())
                ? content.getLast().getId() + ":" + content.getLast().getId()
                : null;

        // 응답 DTO로 포장하기
        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toGetReview).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
    // query 값에 따라 정렬 전략 선택 — 컨트롤러는 이 메서드만 호출한다
    public ReviewResDTO.Pagination<ReviewResDTO.getReview> getMemberReviews(
            Long memberId,
            Integer pageSize,
            String cursor,
            String query
    ) {
        return switch (query.toLowerCase()) {
            case "id" -> getMemberReviewsOrderById(memberId, pageSize, cursor);
            case "score" -> getMemberReviewsOrderByScore(memberId, pageSize, cursor);
            default -> throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
        };
    }

    //별점 순 페이징
    private ReviewResDTO.Pagination<ReviewResDTO.getReview> getMemberReviewsOrderByScore(
            Long memberId,
            Integer pageSize,
            String cursor
    ) {
        PageRequest pageRequest = PageRequest.of(0, pageSize);
        Slice<Review> reviewList;
        String nextCursor;

        if (!cursor.equals("-1")) {
            String[] cursorSplit = cursor.split(":");
            long scoreCursor = Long.parseLong(cursorSplit[0]);
            long idCursor = Long.parseLong(cursorSplit[1]);
            reviewList = reviewRepository.findReviewsByScoreCursor(
                    memberId,
                    scoreCursor,
                    idCursor,
                    pageRequest);
        } else {
            reviewList = reviewRepository.findReviewsByMember_IdOrderByScoreDescIdDesc(memberId, pageRequest);
        }
        List<Review> content = reviewList.getContent();
        nextCursor = (reviewList.hasNext() && !content.isEmpty())
                ? content.getLast().getScore() + ":" + content.getLast().getId()
                : null;

        //응답 DTO로 포장하기
        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toGetReview).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
}