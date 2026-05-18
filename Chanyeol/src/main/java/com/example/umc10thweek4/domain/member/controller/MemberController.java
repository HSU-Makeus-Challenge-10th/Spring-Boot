package com.example.umc10thweek4.domain.member.controller;

import com.example.umc10thweek4.domain.member.dto.MemberResDTO;
import com.example.umc10thweek4.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10thweek4.domain.member.service.MemberService;
import com.example.umc10thweek4.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/v1/users/{userId}")
    public ApiResponse<MemberResDTO.GetInfo> getMyPage(@PathVariable Long userId) {
        return ApiResponse.onSuccess(MemberSuccessCode.MY_PAGE_SUCCESS, memberService.getMyPage(userId));
    }
}
