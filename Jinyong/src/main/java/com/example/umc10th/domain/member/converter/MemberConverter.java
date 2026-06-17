package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Gender;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.global.security.dto.OAuthDTO;

import java.time.LocalDate;

public class MemberConverter {

    // 마이페이지 정보 조회를 위한 DTO 변환
    public static MemberResDTO.GetInfo toGetInfo(Member member) {

        // 빌더 패턴을 사용하여 엔티티의 데이터를 DTO에 매핑합니다.
        return new MemberResDTO.GetInfo(
                member.getName(),
                member.getEmail(),
                null,
                member.getPoint(),
                member.getNickname(),
                member.getGender().name()
        );
    }

    public static Member toMember(MemberReqDTO.SignUp request, String encodedPassword) {
        return Member.builder()
                .email(request.email())
                .password(encodedPassword)
                .name(request.email())
                .nickname(request.email())
                .gender(Gender.NONE)
                .point(0)
                .birth(LocalDate.now())
                .build();
    }

    // 회원가입
    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .email(dto.getSocialEmail())
                .password("")
                .name(dto.getName())
                .nickname(dto.getName())
                .gender(Gender.NONE)
                .point(0)
                .birth(LocalDate.now())
                .socialType(dto.getSocialType())
                .socialUid(dto.getSocialUid())
                .build();
    }

    public static MemberResDTO.SignUp toSignUp(Member member) {
        return new MemberResDTO.SignUp(
                member.getId(),
                member.getEmail()
        );
    }

    
    // 로그인
    public static MemberResDTO.Login toLogin(String accessToken) {
        return new MemberResDTO.Login(accessToken);
    }
}
