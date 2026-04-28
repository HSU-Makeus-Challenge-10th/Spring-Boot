package com.example.umc10th.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ){}
}
