package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MemberController {

    // 마이페이지
    @PostMapping("/v1/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(@RequestBody MemberReqDTO.GetInfo dto) {


        MemberResDTO.GetInfo result = MemberResDTO.GetInfo.builder()
                .name("철수")
                .profileUrl("https://example.com/profile.jpg")
                .email("patrick@hansung.ac.kr")
                .phoneNumber("010-1234-5678")
                .point(22500)
                .build();

        return ApiResponse.onSuccess(result);
    }
}