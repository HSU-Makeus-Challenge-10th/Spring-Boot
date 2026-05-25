package com.example.umc10thweek4.global.security.dto;

import com.example.umc10thweek4.domain.member.enums.SocialType;
import com.example.umc10thweek4.domain.member.exception.MemberException;
import com.example.umc10thweek4.domain.member.exception.code.MemberErrorCode;

import java.util.Map;

public interface OAuthDTO {

    SocialType socialType();

    String socialUid();

    String email();

    String nickname();

    static OAuthDTO from(SocialType socialType, String socialUid, Map<String, Object> attributes) {
        return switch (socialType) {
            case KAKAO -> KakaoDTO.from(socialUid, attributes);
            default -> throw new MemberException(MemberErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
        };
    }
}
