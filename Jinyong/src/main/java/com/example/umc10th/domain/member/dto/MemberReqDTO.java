package com.example.umc10th.domain.member.dto;

public class MemberReqDTO {

    public record GetInfo(
            Long id
    ){}

    // 회원가입
    public record SignUp(
            String email,
            String password
    ){}

    // 로그인
    public record Login(
            String email,
            String password
    ){}
}
