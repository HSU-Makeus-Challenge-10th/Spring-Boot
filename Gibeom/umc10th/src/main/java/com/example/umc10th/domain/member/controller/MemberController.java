package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.enums.MissionStatus;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;
    //마이페이지
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<MemberResDTO.GetInfo>> getInfo(
            @AuthenticationPrincipal Long memberId
    ){
        BaseSuccessCode code = MemberSuccessCode.OK;
        MemberResDTO.GetInfo result = memberService.getInfo(memberId);
        return ResponseEntity
                .status(code.getStatus())
                .body(ApiResponse.onSuccess(code, result));
    }

    // 홈화면
    @GetMapping("/home")
    public ResponseEntity<ApiResponse<MemberResDTO.HomeResultDto>> getHome(
            @AuthenticationPrincipal Long memberId,
            @RequestParam(defaultValue = "0") int page
    ){
        BaseSuccessCode code = MemberSuccessCode.OK;
        MemberResDTO.HomeResultDto result = memberService.getHome(memberId, page);
        return ResponseEntity
                .status(code.getStatus())
                .body(ApiResponse.onSuccess(code, result));
    }

    // 진행중/완료 미션 목록 조회
    @GetMapping("/missions")
    public ResponseEntity<ApiResponse<List<MissionResDTO.MissionDto>>> getMissionsByStatus(
            @AuthenticationPrincipal Long memberId,
            @RequestParam MissionStatus status,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNum,
            @RequestParam (required = false) String sort
    ){
        BaseSuccessCode code = MemberSuccessCode.OK;
        List<MissionResDTO.MissionDto> result = memberService.getMissionsByStatus(memberId, status, pageSize, pageNum, sort);
        return ResponseEntity
                .status(code.getStatus())
                .body(ApiResponse.onSuccess(code, result));
    }

}
