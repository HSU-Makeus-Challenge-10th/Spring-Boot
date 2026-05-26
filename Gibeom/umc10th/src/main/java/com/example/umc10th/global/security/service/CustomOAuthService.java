package com.example.umc10th.global.security.service;


import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.security.dto.KakaoDTO;
import com.example.umc10th.global.security.dto.OAuthDTO;
import com.example.umc10th.global.security.dto.SocialType;
import com.example.umc10th.global.security.entity.OAuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

//OAuth 과정 중 사용자 정보를 가져온 다음 실행되는 서비스
@Service
@RequiredArgsConstructor
public class CustomOAuthService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(
            OAuth2UserRequest userRequest
    ) throws OAuth2AuthenticationException {
        // (필수) 인증 서버의 일회성 토큰을 이용해 정보 조회 & 유저 객체 생성
        OAuth2User oAuthMember = super.loadUser(userRequest);

        // 유저 객체에서 정보 추출
        SocialType providerId;
        String socialUid;
        try {
            providerId = SocialType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
            socialUid = String.valueOf((Long) oAuthMember.getAttribute("id"));
        } catch (IllegalArgumentException e) {
            throw new MemberException(MemberErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
        }

        // OAuth 공통 정보 DTO로 매핑
        OAuthDTO dto;
        switch (providerId) {
            case KAKAO -> {
                Map<String, Object> kakaoAccount = oAuthMember.getAttribute("kakao_account");
                if (kakaoAccount == null) {
                    throw new MemberException(MemberErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
                }
                Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
                String email = kakaoAccount.get("email") != null ? kakaoAccount.get("email").toString() : "";
                String name = (profile != null && profile.get("nickname") != null) ? profile.get("nickname").toString() : "";
                dto = new KakaoDTO(socialUid, name, email);
            }
            default -> throw new MemberException(MemberErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
        }

        // DB 저장: 있다면 그 데이터 가져오고 없으면 새로 저장
        Member member = memberRepository.findBySocialTypeAndSocialUid(providerId, socialUid)
                .orElseGet(() -> {
                    Member newMember = MemberConverter.toMember(dto);
                    memberRepository.save(newMember);
                    return newMember;
                });
        return new OAuthMember(member, oAuthMember.getAttributes());
    }
}
