package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.code.MemberSuccessCode;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/v1/users/me")
    public ResponseEntity<ApiResponse<MemberResDTO.GetInfo>> getInfo(
            @AuthenticationPrincipal AuthMember authMember
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.onSuccess(code, memberService.getInfo(authMember)));
    }

    // 회원가입
    @PostMapping("/v1/auth/signup")
    public ResponseEntity<ApiResponse<MemberResDTO.SignUp>> signUp(
            @RequestBody MemberReqDTO.SignUp request
    ) {
        BaseSuccessCode code = MemberSuccessCode.CREATED;
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.onSuccess(code, memberService.signUp(request)));
    }

    // 로그인
    @PostMapping("/v1/auth/login")
    public ResponseEntity<ApiResponse<MemberResDTO.Login>> login(
            @RequestBody MemberReqDTO.Login request
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.onSuccess(code, memberService.login(request)));
    }
}
