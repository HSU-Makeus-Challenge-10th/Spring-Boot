package com.example.umc10thweek4.domain.review.service;

import com.example.umc10thweek4.domain.member.entity.Member;
import com.example.umc10thweek4.domain.member.repository.MemberRepository;
import com.example.umc10thweek4.domain.mission.entity.mapping.UserMission;
import com.example.umc10thweek4.domain.mission.repository.UserMissionRepository;
import com.example.umc10thweek4.domain.review.dto.ReviewReqDTO;
import com.example.umc10thweek4.domain.review.dto.ReviewResDTO;
import com.example.umc10thweek4.domain.review.entity.Review;
import com.example.umc10thweek4.domain.review.entity.ReviewImage;
import com.example.umc10thweek4.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
                .orElseThrow(() -> new RuntimeException("Member not found"));

        UserMission userMission = userMissionRepository.findByIdAndDeletedAtIsNull(userMissionId)
                .orElseThrow(() -> new RuntimeException("UserMission not found"));

        // 이미 리뷰를 작성했는지 체크
        if (reviewRepository.findByUserMissionIdAndDeletedAtIsNull(userMissionId).isPresent()) {
            throw new RuntimeException("이미 리뷰를 작성했습니다.");
        }

        Review review = Review.builder()
                .member(member)
                .userMission(userMission)
                .content(request.reviewComment())
                .rating(request.reviewRate().floatValue())
                .reviewDate(LocalDateTime.now())
                .build();

        Review savedReview = reviewRepository.save(review);

        // 이미지 처리 (단일 이미지인 경우)
        if (request.reviewImage() != null && !request.reviewImage().isEmpty()) {
            ReviewImage reviewImage = ReviewImage.builder()
                    .review(savedReview)
                    .imageUrl(request.reviewImage())
                    .build();
        }

        return new ReviewResDTO.Create(
                savedReview.getId(),
                (double) savedReview.getRating(),
                savedReview.getReviewDate()
        );
    }
}