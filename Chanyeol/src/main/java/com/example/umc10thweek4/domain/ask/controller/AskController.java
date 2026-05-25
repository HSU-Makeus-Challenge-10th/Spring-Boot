package com.example.umc10thweek4.domain.ask.controller;

import com.example.umc10thweek4.domain.ask.dto.AskReqDTO;
import com.example.umc10thweek4.domain.ask.dto.AskResDTO;
import com.example.umc10thweek4.domain.ask.exception.code.AskSuccessCode;
import com.example.umc10thweek4.domain.ask.service.AskService;
import com.example.umc10thweek4.global.apiPayload.ApiResponse;
import com.example.umc10thweek4.global.security.entity.AuthMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AskController {

    private final AskService askService;

    /**
     * 문의 등록
     */
    @PostMapping("/v1/asks")
    public ResponseEntity<ApiResponse<AskResDTO.Create>> createAsk(
            @AuthenticationPrincipal AuthMember authMember,
            @RequestBody @Valid AskReqDTO.Create request
    ) {
        Long currentMemberId = authMember.getMember().getId();

        AskResDTO.Create response = askService.createAsk(currentMemberId, request);
        return ApiResponse.onSuccessResponse(AskSuccessCode.CREATE_SUCCESS, response);
    }

    /**
     * 내가 작성한 문의 목록
     */
    @GetMapping("/v1/users/me/asks")
    public ResponseEntity<ApiResponse<List<AskResDTO.GetList>>> getMyAsks(
            @AuthenticationPrincipal AuthMember authMember
    ) {
        Long currentMemberId = authMember.getMember().getId();
        List<AskResDTO.GetList> response = askService.getMyAsks(currentMemberId);
        return ApiResponse.onSuccessResponse(AskSuccessCode.LIST_SUCCESS, response);
    }

    @GetMapping("/v1/users/{userId}/asks")
    public ResponseEntity<ApiResponse<List<AskResDTO.GetList>>> getMyAsks(@PathVariable Long userId) {
        List<AskResDTO.GetList> response = askService.getMyAsks(userId);
        return ApiResponse.onSuccessResponse(AskSuccessCode.LIST_SUCCESS, response);
    }

    /**
     * 문의 상세 조회
     */
    @GetMapping("/v1/asks/{askId}")
    public ResponseEntity<ApiResponse<AskResDTO.GetDetail>> getAskDetail(@PathVariable Long askId) {
        AskResDTO.GetDetail response = askService.getAskDetail(askId);
        return ApiResponse.onSuccessResponse(AskSuccessCode.DETAIL_SUCCESS, response);
    }
}
