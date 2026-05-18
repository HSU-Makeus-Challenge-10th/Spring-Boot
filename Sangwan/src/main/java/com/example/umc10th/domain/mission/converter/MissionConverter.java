package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.review.entity.Review;

import java.util.List;

public class MissionConverter {

    public static Review toReview(Member member, MemberMission memberMission,
                                   MissionReqDTO.CreateReviewReq request,
                                   List<String> imageFilenames) {
        Review review = Review.builder()
                .memberMission(memberMission)
                .member(member)
                .rating(request.rating().doubleValue())
                .content(request.content())
                .build();

        if (imageFilenames != null) {
            int limit = Math.min(imageFilenames.size(), 3);
            for (int i = 0; i < limit; i++) {
                review.addReviewImage(imageFilenames.get(i), i + 1);
            }
        }

        return review;
    }

    public static MissionResDTO.CreateReviewRes toCreateReviewRes(Review review, Long missionId) {
        return MissionResDTO.CreateReviewRes.builder()
                .reviewId(review.getId())
                .missionId(missionId)
                .createdAt(review.getCreatedAt())
                .build();
    }
}
