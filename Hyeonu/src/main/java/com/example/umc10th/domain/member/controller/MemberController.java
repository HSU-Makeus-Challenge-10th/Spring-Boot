package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // 마이페이지
    @PostMapping("/v1/members/me")
    public ResponseEntity<ApiResponse<MemberResDTO.GetInfo>> getInfo(
            // 받은 JSON 데이터를 자바 객체(dto)로 변환해서 씀
            // @RequestBody MemberReqDTO.GetInfo dto
            // 헤더에 담긴 토큰을 가지고 사용자 정보 리턴
            @AuthenticationPrincipal AuthMember member
    ){
        return ResponseEntity
                .status(MemberSuccessCode.OK.getStatus())
                .body(ApiResponse.onSuccess(MemberSuccessCode.OK,memberService.getInfo(member)));
    }

    // 회원가입
    @PostMapping("/auth/sign-up")
    public ResponseEntity<ApiResponse<MemberResDTO.SignUpResDTO>> signUp(
            @RequestBody MemberReqDTO.SignUpReqDTO dto
    ){
        return ResponseEntity
                .status(MemberSuccessCode.SIGN_UP.getStatus())
                .body(ApiResponse.onSuccess(MemberSuccessCode.SIGN_UP,memberService.signUp(dto)));
    }

    // 로그인
    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponse<MemberResDTO.LoginResDTO>>login(
            @RequestBody MemberReqDTO.LoginReqDTO dto
    ){
        return ResponseEntity
            .status(MemberSuccessCode.LOGIN.getStatus())
                .body(ApiResponse.onSuccess(MemberSuccessCode.LOGIN,memberService.login(dto)));
}
}
