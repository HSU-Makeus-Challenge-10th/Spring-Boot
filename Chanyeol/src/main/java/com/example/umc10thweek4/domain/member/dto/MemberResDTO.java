package com.example.umc10thweek4.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            Long userId,
            String nickname,
            String phone,
            Integer totalPoints,
            NoticeSetting noticeSettings
    ) {
        public record NoticeSetting(
                boolean getNewEvent,
                boolean getReviewComment,
                boolean getAskComment
        ) {}
    }

    public record SignUp(
            Long userId,
            String nickname
    ) {}
}