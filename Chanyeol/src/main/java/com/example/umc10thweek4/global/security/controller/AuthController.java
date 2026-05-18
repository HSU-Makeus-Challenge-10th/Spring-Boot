package com.example.umc10thweek4.global.security.controller;

import com.example.umc10thweek4.domain.member.dto.MemberReqDTO;
import com.example.umc10thweek4.domain.member.dto.MemberResDTO;
import com.example.umc10thweek4.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10thweek4.domain.member.service.MemberService;
import com.example.umc10thweek4.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignUp> signUp(@RequestBody @Valid MemberReqDTO.SignUp request) {
        return ApiResponse.onSuccess(MemberSuccessCode.SIGN_UP_SUCCESS, memberService.signUp(request));
    }
}
