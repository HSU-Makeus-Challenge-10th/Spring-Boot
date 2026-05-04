package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.MyInfoRes toGetInfo(Member member) {
        return MemberResDTO.MyInfoRes.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getTotalPoint())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(null) // TODO: 프로필 이미지 기능 추가 시 구현
                .build();
    }
}
