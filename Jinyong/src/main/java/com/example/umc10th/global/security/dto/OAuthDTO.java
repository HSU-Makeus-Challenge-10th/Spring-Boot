package com.example.umc10th.global.security.dto;

import com.example.umc10th.global.security.entity.SocialType;

public interface OAuthDTO {
    SocialType getSocialType();
    String getSocialUid();
    String getSocialEmail();
    String getName();
}
