package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
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

    // DTO -> Entity 변환
    public static Member toMember(MemberReqDTO.SignUpReqDTO dto, String encodedPassword) {
        return Member.builder()
                .name(dto.name())
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .detailAddress(dto.detailAddress())
                .phoneNumber(dto.phoneNumber())
                .email(dto.email())
                .password(encodedPassword) // BCrypt 암호화된 비밀번호
                .build();
    }

    public static MemberResDTO.SignUpResDTO toSignUpResDTO(Member member) {
        return new MemberResDTO.SignUpResDTO(member.getId());
    }

    // 로그인
    public static MemberResDTO.LoginResDTO toLoginResDTO(String token){
        return MemberResDTO.LoginResDTO.builder()
                .accessToken(token)
                .build();
    }
}
