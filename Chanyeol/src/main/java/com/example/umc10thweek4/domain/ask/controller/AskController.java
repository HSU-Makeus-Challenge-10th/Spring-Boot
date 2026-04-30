package com.example.umc10thweek4.domain.ask.controller;

import com.example.umc10thweek4.domain.ask.dto.AskReqDTO;
import com.example.umc10thweek4.domain.ask.dto.AskResDTO;
import com.example.umc10thweek4.domain.ask.exception.code.AskSuccessCode;
import com.example.umc10thweek4.global.apiPayload.ApiResponse;
import com.example.umc10thweek4.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AskController {

    @PostMapping("/v1/asks")
    public ApiResponse<AskResDTO> createAsk(@RequestBody AskReqDTO dto) {
        BaseSuccessCode code = AskSuccessCode.OK;
        return ApiResponse.onSuccess(code, null);
    }

    @GetMapping("/v1/asks")
    public ApiResponse<List<AskResDTO>> getMyAsks() {
        BaseSuccessCode code = AskSuccessCode.OK;
        return ApiResponse.onSuccess(code, null);
    }
}