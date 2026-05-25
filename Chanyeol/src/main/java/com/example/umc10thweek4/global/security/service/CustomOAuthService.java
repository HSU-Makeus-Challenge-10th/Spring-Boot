package com.example.umc10thweek4.global.security.service;

import com.example.umc10thweek4.domain.member.converter.MemberConverter;
import com.example.umc10thweek4.domain.member.entity.Member;
import com.example.umc10thweek4.domain.member.entity.mapping.MemberNoticeSetting;
import com.example.umc10thweek4.domain.member.enums.SocialType;
import com.example.umc10thweek4.domain.member.exception.MemberException;
import com.example.umc10thweek4.domain.member.exception.code.MemberErrorCode;
import com.example.umc10thweek4.domain.member.repository.MemberNoticeSettingRepository;
import com.example.umc10thweek4.domain.member.repository.MemberRepository;
import com.example.umc10thweek4.global.security.dto.OAuthDTO;
import com.example.umc10thweek4.global.security.entity.OAuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuthService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final MemberNoticeSettingRepository noticeSettingRepository;

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
            socialUid = String.valueOf(oAuthMember.getAttribute("id"));
        } catch (IllegalArgumentException e) {
            throw new MemberException(MemberErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
        }

        OAuthDTO dto = OAuthDTO.from(providerId, socialUid, oAuthMember.getAttributes());

        // DB 저장: 있다면 그 데이터 가져오고 없으면 새로 저장
        Member member = memberRepository.findBySocialTypeAndSocialUidAndDeletedAtIsNull(providerId, socialUid)
                .or(() -> memberRepository.findActiveByEmail(dto.email()))
                .orElseGet(() -> {
                    Member newMember = memberRepository.save(MemberConverter.toMember(dto));
                    noticeSettingRepository.save(
                            MemberNoticeSetting.builder()
                                    .member(newMember)
                                    .getNewEvent(true)
                                    .getReviewComment(true)
                                    .getAskComment(true)
                                    .build()
                    );
                    return newMember;
                });
        return new OAuthMember(member, oAuthMember.getAttributes());
    }
}
