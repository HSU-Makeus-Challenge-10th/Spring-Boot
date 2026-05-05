package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.MemberMission;
import com.example.umc10th.domain.member.enums.MissionStatus;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.member.repository.MemberMissionRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    //완료된 미션
    @Transactional
    public ReviewResDTO.WriteReviewResultDto writeReview(Long memberId, Long missionId, ReviewReqDTO.WriteReviewDto dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        MemberMission memberMission = memberMissionRepository
                .findByMember_IdAndMission_Id(memberId, missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        if (memberMission.getStatus() != MissionStatus.COMPLETED) {
            throw new MissionException(MissionErrorCode.NOT_COMPLETED);
        }

        Review review = ReviewConverter.toReview(dto, member);
        Review savedReview = reviewRepository.save(review);
        return ReviewConverter.toWriteReviewResult(savedReview);
    }

}
