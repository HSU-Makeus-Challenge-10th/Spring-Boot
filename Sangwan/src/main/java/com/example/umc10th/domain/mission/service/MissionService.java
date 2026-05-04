package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewImage;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewImageRepository;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;

    public MissionResDTO.CreateReviewRes createReview(Long memberId, Long missionId,
                                                       MissionReqDTO.CreateReviewReq request,
                                                       List<MultipartFile> reviewImages) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        MemberMission memberMission = memberMissionRepository
                .findByMemberIdAndMissionId(memberId, missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        if (reviewRepository.existsByMemberMission(memberMission)) {
            throw new ReviewException(ReviewErrorCode.REVIEW_ALREADY_EXISTS);
        }

        Review review = reviewRepository.save(Review.builder()
                .memberMission(memberMission)
                .member(member)
                .rating(request.rating().doubleValue())
                .content(request.content())
                .build());

        if (reviewImages != null && !reviewImages.isEmpty()) {
            int limit = Math.min(reviewImages.size(), 3);
            for (int i = 0; i < limit; i++) {
                reviewImageRepository.save(ReviewImage.builder()
                        .review(review)
                        .imageKey(reviewImages.get(i).getOriginalFilename())
                        .sequence(i + 1)
                        .build());
            }
        }

        return MissionResDTO.CreateReviewRes.builder()
                .reviewId(review.getId())
                .missionId(missionId)
                .createdAt(review.getCreatedAt())
                .build();
    }
}
