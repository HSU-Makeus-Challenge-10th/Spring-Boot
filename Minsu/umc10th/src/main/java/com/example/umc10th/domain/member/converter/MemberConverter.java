package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .name(member.getName())
                .email(member.getEmail())
                .profileUrl(member.getProfileUrl())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }

    public static MemberResDTO.PointInfo toPointInfo(Member member) {
        return MemberResDTO.PointInfo.builder()
                .userId(String.valueOf(member.getId()))
                .nickname(member.getName())
                .point(member.getPoint())
                .build();
    }

    public static MemberResDTO.SignUpInfo toSignUpInfo(Member member) {
        return MemberResDTO.SignUpInfo.builder()
                .userId(String.valueOf(member.getId()))
                .name(member.getName())
                .email(member.getEmail())
                .accessToken("temp_access_token")
                .refreshToken("temp_refresh_token")
                .build();
    }
}
