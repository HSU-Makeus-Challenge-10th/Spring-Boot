package com.example.umc10thweek4.global.security.util;

import com.example.umc10thweek4.global.security.entity.AuthMember;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static AuthMember getCurrentAuthMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof AuthMember
                authMember)) {
            throw new RuntimeException("로그인 정보가 없습니다.");
        }

        return authMember;
    }

    public static Long getCurrentMemberId() {
        return getCurrentAuthMember().getMember().getId();
    }
}
