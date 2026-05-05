package com.example.umc10thweek4.domain.ask.controller;

import com.example.umc10thweek4.domain.ask.dto.AskReqDTO;
import com.example.umc10thweek4.domain.ask.dto.AskResDTO;
import com.example.umc10thweek4.domain.ask.exception.code.AskSuccessCode;
import com.example.umc10thweek4.domain.ask.service.AskService;
import com.example.umc10thweek4.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ApiResponse<AskResDTO.Create> createAsk(@RequestBody @Valid AskReqDTO.Create request) {
        Long currentMemberId = 1L;   // TODO: SecurityContext에서 가져오기

        AskResDTO.Create response = askService.createAsk(currentMemberId, request);
        return ApiResponse.onSuccess(AskSuccessCode.OK, response);
    }

    /**
     * 내가 작성한 문의 목록
     */
    @GetMapping("/v1/users/{userId}/asks")
    public ApiResponse<List<AskResDTO.GetList>> getMyAsks(@PathVariable Long userId) {
        List<AskResDTO.GetList> response = askService.getMyAsks(userId);
        return ApiResponse.onSuccess(AskSuccessCode.OK, response);
    }

    /**
     * 문의 상세 조회
     */
    @GetMapping("/v1/asks/{askId}")
    public ApiResponse<AskResDTO.GetDetail> getAskDetail(@PathVariable Long askId) {
        AskResDTO.GetDetail response = askService.getAskDetail(askId);
        return ApiResponse.onSuccess(AskSuccessCode.OK, response);
    }
}