package com.example.umc10thweek4.domain.review.service;

import com.example.umc10thweek4.domain.member.entity.Member;
import com.example.umc10thweek4.domain.member.repository.MemberRepository;
import com.example.umc10thweek4.domain.mission.entity.mapping.UserMission;
import com.example.umc10thweek4.domain.mission.repository.UserMissionRepository;
import com.example.umc10thweek4.domain.review.converter.ReviewConverter;
import com.example.umc10thweek4.domain.review.dto.ReviewReqDTO;
import com.example.umc10thweek4.domain.review.dto.ReviewResDTO;
import com.example.umc10thweek4.domain.review.entity.Review;
import com.example.umc10thweek4.domain.review.entity.ReviewImage;
import com.example.umc10thweek4.domain.review.exception.ReviewException;
import com.example.umc10thweek4.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10thweek4.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.umc10thweek4.domain.review.dto.ReviewReqDTO.SortType.ID;
import static com.example.umc10thweek4.domain.review.dto.ReviewReqDTO.SortType.RATING;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final UserMissionRepository userMissionRepository;

    /**
     * 리뷰 작성
     */
    @Transactional
    public ReviewResDTO.Create createReview(Long memberId, ReviewReqDTO.Create request, Long userMissionId) {

        Member member = memberRepository.findByIdAndDeletedAtIsNull(memberId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.MEMBER_NOT_FOUND));

        UserMission userMission = userMissionRepository.findByIdAndDeletedAtIsNull(userMissionId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.USER_MISSION_NOT_FOUND));

        // 이미 리뷰를 작성했는지 체크
        if (reviewRepository.findByUserMissionIdAndDeletedAtIsNull(userMissionId).isPresent()) {
            throw new ReviewException(ReviewErrorCode.REVIEW_ALREADY_EXISTS);
        }

        Review review = Review.builder()
                .member(member)
                .userMission(userMission)
                .content(request.reviewComment())
                .rating(request.reviewRate().floatValue())
                .reviewDate(LocalDateTime.now())
                .build();

        Review savedReview = reviewRepository.save(review);

        // 이미지 처리
        if (request.imageUrls() != null && !request.imageUrls().isEmpty()) {
            List<ReviewImage> reviewImages = request.imageUrls().stream()
                    .map(imageUrl -> ReviewImage.builder()
                            .review(savedReview)
                            .imageUrl(imageUrl)
                            .build())
                    .toList();

            // reviewImageRepository.saveAll(reviewImages);
        }

        return ReviewConverter.toCreateRes(savedReview);
    }

    /**
     * 작성한 리뷰 조회
     */
    public ReviewResDTO.Pagination<ReviewResDTO.GetReviewList> getMyReviews(
            Long memberId, Integer pageSize, String cursor) {

        Long cursorId = null;
        if (cursor != null && !cursor.trim().isEmpty()) {
            try {
                cursorId = Long.parseLong(cursor);
            } catch (NumberFormatException e) {
                cursorId = null;
            }
        }

        // Pageable 생성 (한 개 더 조회해서 hasNext 판단)
        Slice<Review> slice = reviewRepository.findMyReviews(
                memberId,
                cursorId,
                PageRequest.of(0, pageSize + 1)
        );

        List<Review> reviews = slice.getContent();
        boolean hasNext = reviews.size() > pageSize;

        // 실제로 반환할 데이터
        List<Review> resultList = hasNext ? reviews.subList(0, pageSize) : reviews;

        // nextCursor 생성
        String nextCursor = null;
        if (hasNext && !resultList.isEmpty()) {
            nextCursor = String.valueOf(resultList.get(resultList.size() - 1).getId());
        }

        List<ReviewResDTO.GetReviewList> data = resultList.stream()
                .map(ReviewConverter::toGetListRes)
                .toList();

        return ReviewConverter.toPagination(data, hasNext, nextCursor, pageSize);
    }
}