package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.MemberSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MissionController {
    private final MissionService missionService;

    // 미션 도전하기 API
    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MissionResDTO.MissionChallengeResult> challengeMission(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.ChallengeDTO request
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.OK,missionService.challengeMission(missionId, request));
    }

    // 내가 진행중이거나 완료한 미션 목록 조회(페이징 포함)
    @GetMapping("/members/{memberId}")
    public ApiResponse<Page<MissionResDTO.MissionSummaryDTO>> getMemberMissions(
            @PathVariable Long memberId,
            @RequestParam(name="status") MissionStatus status,
            @RequestParam(name="page",defaultValue="0")Integer page
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.OK, missionService.getMemberMissionList(memberId, status, page));
    }

    // 특정 지역의 도전 가능한 미션 목록 조회(페이징 포함)
    @GetMapping("/regions/{regionId}")
    public ApiResponse<Page<MissionResDTO.MissionSummaryDTO>> getRegionMissions(
            @PathVariable Long regionId,
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, missionService.getMissionListByRegion(regionId, page));
    }
}
