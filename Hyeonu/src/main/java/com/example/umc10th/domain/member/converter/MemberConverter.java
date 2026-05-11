package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {
    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .name(member.getName())
                .gender(member.getGender())
                .birth(member.getBirth())
                .address(member.getAddress())
                .detailAddress(member.getDetailAddress())
                .phoneNumber(member.getPhoneNumber())
                .email(member.getEmail())
                .point(member.getPoint())
                .status(member.getStatus())
                .build();
    }
}
