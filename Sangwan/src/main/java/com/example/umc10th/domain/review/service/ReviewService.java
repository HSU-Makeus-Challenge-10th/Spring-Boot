package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.global.dto.CursorPageRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewResDTO.CreateReviewRes createReview(Long missionId, ReviewReqDTO.CreateReviewReq request, List<MultipartFile> reviewImages) {
        // TODO: 리뷰 작성 로직
        return null;
    }

    public CursorPageRes<ReviewResDTO.ReviewItem> getMyReviews(Long memberId, Long cursor, Double ratingCursor,
                                                               String sort, int size) {
        PageRequest pageable = PageRequest.of(0, size + 1);
        Slice<Review> slice;

        if ("rating".equalsIgnoreCase(sort)) {
            slice = (ratingCursor != null)
                    ? reviewRepository.findByMemberIdWithRatingCursor(memberId, ratingCursor, pageable)
                    : reviewRepository.findByMemberIdOrderByRatingDescIdDesc(memberId, pageable);
        } else {
            slice = (cursor != null)
                    ? reviewRepository.findByMemberIdAndIdCursorOrderByIdDesc(memberId, cursor, pageable)
                    : reviewRepository.findByMemberIdOrderByIdDesc(memberId, pageable);
        }

        List<Review> content = slice.getContent();
        boolean hasNext = content.size() > size;
        List<Review> reviews = hasNext ? content.subList(0, size) : content;

        Long nextCursor = null;
        Double nextRatingCursor = null;

        if (hasNext) {
            Review last = reviews.get(reviews.size() - 1);
            if ("rating".equalsIgnoreCase(sort)) {
                nextRatingCursor = last.getRating();
            } else {
                nextCursor = last.getId();
            }
        }

        return ReviewConverter.toReviewListRes(
                reviews, hasNext, nextCursor, nextRatingCursor,
                cursor == null && ratingCursor == null, !hasNext);
    }
}
