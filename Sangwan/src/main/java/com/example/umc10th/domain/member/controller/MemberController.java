package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignupRes> signup(
            @Valid @RequestBody MemberReqDTO.SignupReq request
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.SIGNUP, memberService.signup(request));
    }

    // 홈화면 조회
    @GetMapping("/me/home")
    public ApiResponse<MemberResDTO.HomeRes> getHome(
            @RequestParam Long memberId  // TODO: 인증 구현 후 SecurityContext로 대체
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.HOME, memberService.getHome(memberId));
    }

    // 미션 목록 조회 (진행중 / 진행완료)
    @GetMapping("/me/missions")
    public ApiResponse<MemberResDTO.MissionListRes> getMissions(
            @RequestParam Long memberId,  // TODO: 인증 구현 후 SecurityContext로 대체
            @RequestParam String status,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.MISSION_LIST,
                memberService.getMissions(memberId, status, cursor, size));
    }

    // 미션 성공 누르기
    @PatchMapping("/me/missions/{missionId}/success")
    public ApiResponse<MemberResDTO.MissionSuccessRes> requestMissionSuccess(
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.MISSION_SUCCESS_REQUEST,
                memberService.requestMissionSuccess(missionId));
    }

    // 내 정보 가져오기
    @PostMapping("/me")
    public ApiResponse<MemberResDTO.MyInfoRes> getMyInfo(
            @Valid @RequestBody MemberReqDTO.MyInfoReq myInfoReq
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.MYINFO, memberService.requestMyInfo(myInfoReq));
    }
}
