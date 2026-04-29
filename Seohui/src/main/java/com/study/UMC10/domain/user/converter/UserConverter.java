package com.study.UMC10.domain.user.converter;

import com.study.UMC10.domain.user.dto.response.UserResponseDto;
import com.study.UMC10.domain.user.entity.User;

public class UserConverter {

    public static UserResponseDto.GetInfo toGetInfo(User user) {
        return UserResponseDto.GetInfo.builder()
                .name(user.getName())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .phoneNum(user.getPhoneNum())
                .totalPoint(user.getTotalPoint())
                .build();
    }
}