package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;


    //리뷰 작성 (완료된 미션에 한해서)
    @PostMapping("v1/missions/{missionId}/reviews")
    public ResponseEntity<ApiResponse<ReviewResDTO.WriteReviewResultDto>> writeReview(
            @PathVariable Long missionId,
            @AuthenticationPrincipal Long memberId,
            @RequestBody ReviewReqDTO.WriteReviewDto dto) {
        BaseSuccessCode code = ReviewSuccessCode.WRITE_SUCCESS;
        ReviewResDTO.WriteReviewResultDto result = missionService.writeReview(memberId, missionId, dto);
        return ResponseEntity
                .status(code.getStatus())
                .body(ApiResponse.onSuccess(code, result));
    }

    //가게 미션 생성
    @PostMapping("v1/stores/{storeId}/missions")
    public ResponseEntity<ApiResponse<MissionResDTO.CreateMissionResult>>  createMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission dto
    ){
        MissionSuccessCode code = MissionSuccessCode.CREATED;
        MissionResDTO.CreateMissionResult result = missionService.createMission(storeId, dto);
        return ResponseEntity
                .status(code.getStatus())
                .body(ApiResponse.onSuccess(code, result));
    }

    //가게 미션들 조회 (오프셋 기반)
    @GetMapping("v1/stores/{storeId}/missions")
    public ResponseEntity<ApiResponse<Page<MissionResDTO.GetMission>>> getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        Page<MissionResDTO.GetMission> result = missionService.getMissions(storeId, pageSize, pageNumber, sort);
        return ResponseEntity
                .status(code.getStatus())
                .body(ApiResponse.onSuccess(code, result));
    }

    //내가 진행중인 미션 조회하기
    @GetMapping("v1/members/{memberId}/missions")
    public ResponseEntity<ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>>> getMemberMissions(
            @PathVariable Long memberId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        MissionResDTO.Pagination<MissionResDTO.GetMission> result = missionService.getMemberMissions(memberId, pageSize, pageNumber, sort);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.onSuccess(code, result));
    }
}
