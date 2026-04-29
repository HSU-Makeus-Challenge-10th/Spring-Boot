package com.umc.study.domain.user.web.dto;

public record GetMyPageRes(
        String profileUrl,
        String nickname,
        String email,
        String phoneNum,
        Integer point
) {
}
