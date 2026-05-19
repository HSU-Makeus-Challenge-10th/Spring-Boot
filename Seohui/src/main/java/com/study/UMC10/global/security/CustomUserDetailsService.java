package com.study.UMC10.global.security;

import com.study.UMC10.domain.user.code.UserErrorCode;
import com.study.UMC10.domain.user.code.UserException;
import com.study.UMC10.domain.user.entity.User;
import com.study.UMC10.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserException(UserErrorCode.MEMBER_NOT_FOUND));

        return new CustomUserDetails(user);
    }
}