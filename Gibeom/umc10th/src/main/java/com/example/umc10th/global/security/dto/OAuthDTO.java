package com.example.umc10th.global.security.dto;


//공통 DTO (OAuth 제공자에 따라 정보가 다를 것을 대비한 OAuthDTO)
public interface OAuthDTO {
    SocialType getSocialType();
    String getSocialUid();
    String getSocialEmail();
    String getName();
}
