package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "유저 API")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/me")
    @Operation(summary = "유저 정보 조회")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(@RequestBody MemberReqDTO.GetInfo dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberService.getInfo(dto));
    }

    @GetMapping("/points")
    @Operation(summary = "포인트 조회")
    public ApiResponse<MemberResDTO.PointInfo> getPoints(
            @RequestHeader(value = "Authorization", required = false) String token) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, memberService.getPoints(token));
    }
}
