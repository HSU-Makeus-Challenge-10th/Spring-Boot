package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.MemberSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MissionController {
    private final MissionService missionService;

    // 내가 진행중이거나 완료한 미션 목록 조회(페이징 포함)
    @GetMapping("/{memberId}/missions")
    public ApiResponse<MissionResDTO.MissionListResDTO> getMemberMissions(
            @PathVariable Long memberId,
            @ModelAttribute MissionReqDTO.MissionListReqDTO dto
    ){
        // 서비스에서 데이터 가져오기
        return ApiResponse.onSuccess(MissionSuccessCode.OK, missionService.getMemberMissionList(memberId, dto));
    }

}
