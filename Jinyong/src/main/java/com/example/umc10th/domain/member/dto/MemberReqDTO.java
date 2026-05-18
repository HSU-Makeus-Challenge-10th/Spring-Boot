package com.example.umc10th.domain.member.dto;

public class MemberReqDTO {

    public record GetInfo(
            Long id
    ){}

    public record SignUp(
            String email,
            String password
    ){}
}
