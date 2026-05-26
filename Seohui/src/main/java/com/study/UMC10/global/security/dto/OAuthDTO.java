package com.study.UMC10.global.security.dto;

import com.study.UMC10.domain.user.enums.LoginType;

public interface OAuthDTO {
    LoginType getLoginType();
    String getSocialId();
    String getEmail();
    String getName();
}