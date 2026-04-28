package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MemberController {

    // 1. 회원가입 API
    @PostMapping("/auth/signup")
    public ApiResponse<MemberResDTO.JoinResultDTO> join(@RequestBody MemberReqDTO.JoinDTO request) {
        // 명세서에 맞춘 가짜 데이터 생성
        MemberResDTO.JoinResultDTO dummy = MemberResDTO.JoinResultDTO.builder()
                .userId(1L)
                .nickname(request.getNickname())
                .email(request.getEmail())
                .build();
        return ApiResponse.onSuccess(dummy);
    }

    // 2. 홈 화면 조회 API
    @GetMapping("/home")
    public ApiResponse<MemberResDTO.HomeResultDTO> getHome() {
        MemberResDTO.MissionDetailDTO dummyMission = MemberResDTO.MissionDetailDTO.builder()
                .userMissionId(1L)
                .missionTitle("10,000원 이상 주문 시 500P 적립")
                .reward(500)
                .status("IN_PROGRESS")
                .build();

        MemberResDTO.HomeResultDTO dummyHome = MemberResDTO.HomeResultDTO.builder()
                .nickname("nickname012")
                .point(22500)
                .missions(List.of(dummyMission))
                .build();

        return ApiResponse.onSuccess(dummyHome);
    }
}
