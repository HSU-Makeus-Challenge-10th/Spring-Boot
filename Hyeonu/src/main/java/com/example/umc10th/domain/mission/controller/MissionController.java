package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.MemberSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MissionController {
    private final MissionService missionService;

    // 내가 진행중이거나 완료한 미션 목록 조회(페이징 포함)
    @PostMapping("/members/missions")
    public ResponseEntity<ApiResponse<MissionResDTO.MissionListResDTO>> getMemberMissions(
            @RequestBody MissionReqDTO.MissionListReqDTO dto
    ){
        // 서비스에서 데이터 가져오기
        return ResponseEntity
                .status(MissionSuccessCode.OK.getStatus())
                .body(ApiResponse.onSuccess(MissionSuccessCode.OK, missionService.getMemberMissionList(dto)));
    }

    // 현재 선택된 지역에서 도전 가능한 미션 목록(페이징 포함)
    @GetMapping("/regions/{regionId}/missions")
    public ResponseEntity<ApiResponse<MissionResDTO.RegionMissionListDTO>>getRegionMissions(
            @PathVariable Long regionId,
            @ModelAttribute MissionReqDTO.MyMissionReqDTO dto
    ){
        return ResponseEntity
                .status(MissionSuccessCode.OK.getStatus())
                .body(ApiResponse.onSuccess(MissionSuccessCode.OK,missionService.getRegionMissionList(regionId,dto)));
    }

    // 가게 미션 생성
    @PostMapping("/stores/{storeId}/missions")
    public ResponseEntity<ApiResponse<Void>> createMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission dto // Valid 검증 어노테이션떄매 사용
    ){
        return ResponseEntity
                .status(MissionSuccessCode.CREATED.getStatus())
                .body(ApiResponse.onSuccess(MissionSuccessCode.CREATED,missionService.createMission(storeId,dto)));
    }

    // 가게 내 미션들 조회
    @GetMapping("/stores/{storeId}/missions")
    public ResponseEntity<ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>>>getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize, // 한 페이지에 몇개 보여줄지
            @RequestParam String cursor,
            @RequestParam String query
    ){
        return ResponseEntity
                .status(MissionSuccessCode.OK.getStatus())
                .body(ApiResponse.onSuccess(MissionSuccessCode.OK,missionService.getMissions(storeId,pageSize,cursor,query)));
    }
}
