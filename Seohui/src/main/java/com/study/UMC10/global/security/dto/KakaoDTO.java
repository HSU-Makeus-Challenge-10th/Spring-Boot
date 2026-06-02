package com.study.UMC10.global.security.dto;

import com.study.UMC10.domain.user.enums.LoginType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KakaoDTO implements OAuthDTO {
    private final String id;
    private final String email;
    private final String name;

    @Override
    public LoginType getLoginType() {
        return LoginType.KAKAO;
    }

    @Override
    public String getSocialId() {
        return id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getName() {
        return name;
    }
}