package com.example.umc10th.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {
    @Builder
    public record GetInfo(
            String name,
            String email,
            String phoneNumber,
            Integer point,
            String nickname,
            String gender
    ) {}

    // 회원가입
    @Builder
    public record SignUp(
            Long memberId,
            String email
    ) {}

    // 로그인
    @Builder
    public record Login(
            String accessToken
    ) {}
}
