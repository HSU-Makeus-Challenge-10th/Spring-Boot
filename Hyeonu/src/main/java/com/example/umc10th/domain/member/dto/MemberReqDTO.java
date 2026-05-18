package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Address;
import com.example.umc10th.domain.member.enums.Gender;

import java.time.LocalDate;

public class MemberReqDTO {

    // 마이페이지
    public record GetInfo(
            Long id
    ){}

    // 회원가입
    public record SignUpReqDTO(
            String name,
            Gender gender,
            LocalDate birth,
            Address address,
            String detailAddress,
            String phoneNumber,
            String email,
            String password
    ){}
}
