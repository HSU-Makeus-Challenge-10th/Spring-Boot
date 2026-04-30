package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {
    public static MemberResDTO.MyInfoRes toGetInfo(Member member) {
        return MemberResDTO.MyInfoRes.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .phoneNumber(member.getPhone())
                .profileUrl(member.getProfileUrl())
                .build();

    }
}
