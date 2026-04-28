package com.example.umc10th.domain.member.dto;

import java.util.List;

public class MemberReqDTO {

    public record GetInfo(Long id) {}

    public record SignUp(
            String name,
            String gender,
            String birthDate,
            String detailAddress,
            String email,
            String phoneNumber,
            List<Long> foodTypeIds,
            List<Long> termsIds,
            String socialType,
            String socialToken
    ) {}
}
