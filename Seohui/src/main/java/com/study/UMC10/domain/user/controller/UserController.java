package com.study.UMC10.domain.user.controller;

import com.study.UMC10.domain.user.code.UserSuccessCode;
import com.study.UMC10.domain.user.dto.request.UserRequestDto;
import com.study.UMC10.domain.user.dto.response.UserResponseDto;
import com.study.UMC10.domain.user.service.UserService;
import com.study.UMC10.global.apiPayload.ApiResponse;
import com.study.UMC10.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "User API", description = "유저 관련 API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "마이페이지 API", description = "유저의 마이페이지 정보를 조회하는 API입니다.")
    @PostMapping("/v1/users/me")
    public ApiResponse<UserResponseDto.GetInfo> getInfo(
            @RequestBody UserRequestDto.GetInfo dto
    ) {
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, userService.getInfo(dto));
    }

    @Operation(summary = "회원가입 API", description = "새로운 유저를 등록하는 API입니다.")
    @PostMapping("/auth/sign-up")
    public ApiResponse<UserResponseDto.SignUpResultDto> signUp(
            @RequestBody UserRequestDto.SignUpDto requestDto
    ) {
        BaseSuccessCode code = UserSuccessCode.SIGNUP_SUCCESS;
        return ApiResponse.onSuccess(code, userService.signUp(requestDto));
    }

    @Operation(summary = "홈 화면 조회 API", description = "특정 지역의 미션 목록 및 유저 정보를 포함한 홈 화면 데이터를 조회합니다.")
    @Parameters({
            @Parameter(name = "region", description = "조회할 동네 이름", example = "안암동"),
            @Parameter(name = "page", description = "페이지 번호", example = "0")
    })
    @GetMapping("/v1/home")
    public ApiResponse<UserResponseDto.HomeResultDto> getHome(
            @RequestParam(name = "region", defaultValue = "안암동") String region,
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ) {
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, userService.getHome(region, page));
    }
}