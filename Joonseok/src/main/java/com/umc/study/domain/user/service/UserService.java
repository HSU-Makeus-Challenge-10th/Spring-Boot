package com.umc.study.domain.user.service;

import com.umc.study.domain.user.entity.User;
import com.umc.study.domain.user.exception.UserNotFoundException;
import com.umc.study.domain.user.repository.UserRepository;
import com.umc.study.domain.user.web.dto.GetMyPageRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public GetMyPageRes getMyPage(Long userId) {

        User found = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return new GetMyPageRes(
                found.getName(),
                found.getEmail(),
                found.getPhoneNum() == null ? "" : found.getPhoneNum(),
                found.getPointDeposit()
        );
    }
}
