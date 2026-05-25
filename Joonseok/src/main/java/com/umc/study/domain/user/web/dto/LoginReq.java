package com.umc.study.domain.user.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginReq {

    private final String loginId;
    private final String password;
}
