package com.umc.study.domain.user.web.controller;

import com.umc.study.global.apiPayload.ApiResponse;
import com.umc.study.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    // private final Userservice userService;

    @GetMapping("/home")
    public ResponseEntity<ApiResponse<?>> getMain(
            // TODO JWT에서 유저를 추출해서 주입
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {

        // TODO Service 객체에 실제 비즈니스 로직을 채우고, DI 받은 객체로 호출하여, 응답을 반환받도록 함.

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.onComplete(
                        GeneralSuccessCode.OK,
                        null
                ));
    }
}
