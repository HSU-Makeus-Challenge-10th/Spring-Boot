package com.example.umc10thweek4.global.security.service;

import com.example.umc10thweek4.domain.member.entity.Member;
import com.example.umc10thweek4.domain.member.exception.MemberException;
import com.example.umc10thweek4.domain.member.exception.code.MemberErrorCode;
import com.example.umc10thweek4.domain.member.repository.MemberRepository;
import com.example.umc10thweek4.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        Member member = memberRepository.findActiveByEmail(username).orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return new AuthMember(member);
    }
}
