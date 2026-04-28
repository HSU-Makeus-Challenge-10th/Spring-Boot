package com.umc.study.domain.user.web.controller;

import com.umc.study.domain.user.exception.code.UserSuccessCode;
import com.umc.study.global.apiPayload.ApiResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    // private final Userservice userService;

    @GetMapping("/home")
    public ResponseEntity<ApiResponse<?>> getMain(
            // TODO JWT에서 유저를 추출해서 주입
            @RequestParam @Min(message = "page값은 1 이상이어야 합니다.", value = 1) Integer page,
            @RequestParam @Min(message = "size값은 1 이상이어야 합니다.", value = 1) @Max(message = "size값은 10을 넘길 수 없습니다.", value = 10) Integer size
    ) {

        // TODO Service 객체에 실제 비즈니스 로직을 채우고, DI 받은 객체로 호출하여, 응답을 반환받도록 함.

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.onComplete(
                        UserSuccessCode.HOME_OK,
                        null
                ));
    }
}
