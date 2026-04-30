package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {

    // 마이페이지 정보 조회를 위한 DTO 변환
    public static MemberResDTO.GetInfo toGetInfo(Member member) {

        // 빌더 패턴을 사용하여 엔티티의 데이터를 DTO에 매핑합니다.
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(member.getProfileUrl())
                .build();
    }
}
