package com.example.umc10th.global.security.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.security.dto.SocialType;
import com.example.umc10th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService{

    private final MemberRepository memberRepository;

    public UserDetails loadUserByUidAndSocialType(
            SocialType socialType,
            String username
    ) throws UsernameNotFoundException{
        Member member = memberRepository.findBySocialTypeAndSocialUid(socialType, username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        return new AuthMember(member);
    }
}
