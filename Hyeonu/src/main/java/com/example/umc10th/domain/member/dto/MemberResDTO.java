package com.example.umc10th.domain.member.dto;

import lombok.Builder;

import java.time.LocalDate;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            String name,
            Enum gender,
            LocalDate birth,
            String address,
            String detailAddress,
            String phoneNumber,
            String email,
            Integer point,
            Enum status
    ){}
}
