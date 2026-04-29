package com.study.UMC10.domain.user.service;

import com.study.UMC10.domain.user.code.UserErrorCode;
import com.study.UMC10.domain.user.code.UserException;
import com.study.UMC10.domain.user.converter.UserConverter;
import com.study.UMC10.domain.user.dto.request.UserRequestDto;
import com.study.UMC10.domain.user.dto.response.UserResponseDto;
import com.study.UMC10.domain.user.entity.User;
import com.study.UMC10.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto.GetInfo getInfo(UserRequestDto.GetInfo dto) {
        Long userId = dto.id();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.MEMBER_NOT_FOUND));

        return UserConverter.toGetInfo(user);
    }

    // 회원가입
    public UserResponseDto.SignUpResultDto signUp(UserRequestDto.SignUpDto requestDto) {

        return null;
    }

    // 홈 화면
    public UserResponseDto.HomeResultDto getHome() {
        return null;
    }
}