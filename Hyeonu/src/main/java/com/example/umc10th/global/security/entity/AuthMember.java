package com.example.umc10th.global.security.entity;

import com.example.umc10th.domain.member.entity.Member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter // 모든 필드의 getter 자동 생성
@RequiredArgsConstructor // final 필드를 받는 생성자 자동 생성
// Member를 Security용으로 포장한 Wrapper 역할
public class AuthMember implements UserDetails {

    private final Member member;

    // 자신의 역할 반환(유저, 관리자)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(); // 권한 구분 미구현 상태(빈 리스트 반환)
    }

    @Override
    public @Nullable String getPassword(){ // @Nullable은 비밀번호가 없을 수도 있음(소셜 로그인 등)
        return member.getPassword(); // Member 엔티티의 비밀번호 반환
    }

    @Override
    public String getUsername(){ // Spring Security 규칙상 만든것
        return member.getEmail(); // 이메일을 username으로 사용(식별자로)
    }

    public String getUserEmail(){
        return member.getEmail();
    }
}
