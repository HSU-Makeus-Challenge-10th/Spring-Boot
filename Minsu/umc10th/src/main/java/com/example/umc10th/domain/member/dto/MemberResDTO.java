package com.example.umc10th.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ) {}

    @Builder
    public record PointInfo(
            @JsonProperty("user_id") String userId,
            String nickname,
            Integer point
    ) {}

    @Builder
    public record SignUpInfo(
            @JsonProperty("user_id") String userId,
            String name,
            String email,
            @JsonProperty("access_token") String accessToken,
            @JsonProperty("refresh_token") String refreshToken
    ) {}
}
