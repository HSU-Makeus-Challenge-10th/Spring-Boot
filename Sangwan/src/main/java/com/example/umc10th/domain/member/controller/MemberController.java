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
        BaseSuccessCode code = MemberSuccessCode.SIGNUP;
        return ApiResponse.onSuccess(code, memberService.signup(request));
    }

    // 홈화면
    @GetMapping("/me/home")
    public ApiResponse<MemberResDTO.HomeRes> getHome() {
        BaseSuccessCode code = MemberSuccessCode.HOME;
        return ApiResponse.onSuccess(code, memberService.getHome());
    }

    // 미션 목록 조회 (진행중 / 진행완료)
    @GetMapping("/me/missions")
    public ApiResponse<MemberResDTO.MissionListRes> getMissions(
            @RequestParam String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        BaseSuccessCode code = MemberSuccessCode.MISSION_LIST;
        return ApiResponse.onSuccess(code, memberService.getMissions(status, page, size));
    }

    // 미션 성공 누르기
    @PatchMapping("/me/missions/{missionId}/success")
    public ApiResponse<MemberResDTO.MissionSuccessRes> requestMissionSuccess(
            @PathVariable Long missionId
    ) {
        BaseSuccessCode code = MemberSuccessCode.MISSION_SUCCESS_REQUEST;
        return ApiResponse.onSuccess(code, memberService.requestMissionSuccess(missionId));
    }
    // 내 정보 가져오기
    @PostMapping("/me")
    public ApiResponse<MemberResDTO.MyInfoRes> getMyInfo(@RequestBody MemberReqDTO.MyInfoReq myInfoReq) {
        BaseSuccessCode code = MemberSuccessCode.MYINFO;
        return ApiResponse.onSuccess(code, memberService.requestMyInfo(myInfoReq));
    }
}
