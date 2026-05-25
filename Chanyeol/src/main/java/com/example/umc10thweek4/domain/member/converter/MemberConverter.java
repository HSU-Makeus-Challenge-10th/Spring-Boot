package com.example.umc10thweek4.domain.member.converter;

import com.example.umc10thweek4.domain.member.dto.MemberResDTO;
import com.example.umc10thweek4.domain.member.entity.Member;
import com.example.umc10thweek4.domain.member.entity.mapping.MemberNoticeSetting;
import com.example.umc10thweek4.domain.member.enums.Gender;
import com.example.umc10thweek4.global.security.dto.OAuthDTO;

import java.time.LocalDate;

public class MemberConverter {

    public static MemberResDTO.SignUp toSignUpRes(Member member) {
        return new MemberResDTO.SignUp(
                member.getId(),
                member.getNickname()
        );
    }

    public static MemberResDTO.GetInfo toGetInfoRes(Member member, MemberNoticeSetting ns) {
        return MemberResDTO.GetInfo.builder()
                .userId(member.getId())
                .nickname(member.getNickname())
                .phone(member.getPhoneNumber())
                .totalPoints(member.getTotalPoints() != null
                        ? member.getTotalPoints().intValue() : 0)
                .noticeSettings(new MemberResDTO.GetInfo.NoticeSetting(
                        ns.getGetNewEvent(),
                        ns.getGetReviewComment(),
                        ns.getGetAskComment()
                ))
                .build();
    }

    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .name(dto.nickname())
                .nickname(dto.socialType().name().toLowerCase() + "_" + dto.socialUid())
                .email(dto.email())
                .password("")
                .birth(LocalDate.of(1900, 1, 1))
                .gender(Gender.NONE)
                .socialUid(dto.socialUid())
                .socialType(dto.socialType())
                .build();
    }
}
