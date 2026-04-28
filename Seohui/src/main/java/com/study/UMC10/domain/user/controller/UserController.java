package com.study.UMC10.domain.user.controller;

import com.study.UMC10.domain.user.code.UserSuccessCode;
import com.study.UMC10.domain.user.dto.request.UserRequestDto;
import com.study.UMC10.domain.user.dto.response.UserResponseDto;
import com.study.UMC10.domain.user.service.UserService;
import com.study.UMC10.global.apiPayload.ApiResponse;
import com.study.UMC10.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // 마이페이지
    @PostMapping("/v1/users/me")
    public ApiResponse<UserResponseDto.GetInfo> getInfo(
            @RequestBody UserRequestDto.GetInfo dto
    ) {
        BaseSuccessCode code = UserSuccessCode.OK;

        return ApiResponse.onSuccess(code, userService.getInfo(dto));
    }

    // 회원가입
    @PostMapping("/auth/sign-up")
    public ApiResponse<UserResponseDto.SignUpResultDto> signUp(
            @RequestBody UserRequestDto.SignUpDto requestDto
    ) {

        BaseSuccessCode code = UserSuccessCode.SIGNUP_SUCCESS;

        return ApiResponse.onSuccess(code, userService.signUp(requestDto));
    }

    @GetMapping("/v1/home")
    public ApiResponse<UserResponseDto.HomeResultDto> getHome() {
        BaseSuccessCode code = UserSuccessCode.OK;

        return ApiResponse.onSuccess(code, userService.getHome());
    }
}