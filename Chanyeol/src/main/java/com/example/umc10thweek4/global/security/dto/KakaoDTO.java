package com.example.umc10thweek4.global.security.dto;

import com.example.umc10thweek4.domain.member.enums.SocialType;
import com.example.umc10thweek4.domain.member.exception.MemberException;
import com.example.umc10thweek4.domain.member.exception.code.MemberErrorCode;

import java.util.Map;

public record KakaoDTO(
        String socialUid,
        String email,
        String nickname
) implements OAuthDTO {

    @Override
    public SocialType socialType() {
        return SocialType.KAKAO;
    }

    @SuppressWarnings("unchecked")
    public static KakaoDTO from(String socialUid, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = kakaoAccount == null
                ? null
                : (Map<String, Object>) kakaoAccount.get("profile");

        String email = getRequiredString(kakaoAccount, "email");
        String nickname = getRequiredString(profile, "nickname");

        return new KakaoDTO(socialUid, email, nickname);
    }

    private static String getRequiredString(Map<String, Object> attributes, String key) {
        if (attributes == null || attributes.get(key) == null) {
            throw new MemberException(MemberErrorCode.OAUTH_REQUIRED_ATTRIBUTE_NOT_FOUND);
        }
        return attributes.get(key).toString();
    }
}
