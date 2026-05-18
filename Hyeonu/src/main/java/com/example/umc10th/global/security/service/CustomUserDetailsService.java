package com.example.umc10th.global.security.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // 스프링 서비스 빈 등록
@RequiredArgsConstructor // final 필드 생성자 자동 생성
// Spring Security가 "이 인터페이스를 구현한 클래스로 사용자를 조회해라"
public class CustomUserDetailsService implements UserDetailsService {

    // DB에서  회원을 조회하기 위한 Repository
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(
            String username
    )throws UsernameNotFoundException{
        // 이메일로 DB에서 회원 조회
        Member member=memberRepository.findByEmail(username)
                .orElseThrow(()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        // AuthMember로 감싸서 반환
        return new AuthMember(member);
    }
}
