package com.example.umc10thweek4.domain.member.controller;

import com.example.umc10thweek4.domain.member.dto.MemberReqDTO;
import com.example.umc10thweek4.domain.member.dto.MemberResDTO;
import com.example.umc10thweek4.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10thweek4.global.apiPayload.ApiResponse;
import com.example.umc10thweek4.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    @PostMapping("/v1/users")
    public ApiResponse<MemberResDTO> signUp(@RequestBody @Valid MemberReqDTO.SignUp request) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, null);
    }

    @GetMapping("/v1/users/{userId}")
    public ApiResponse<MemberResDTO> getMyPage(@PathVariable Long userId) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, null);
    }
}