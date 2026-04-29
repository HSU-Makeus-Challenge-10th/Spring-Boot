package com.umc.study.domain.user.web.controller;

import com.umc.study.domain.user.exception.code.UserSuccessCode;
import com.umc.study.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    // private final Userservice userService;

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<?>> signIn(
            @Valid @RequestBody Object request
    ) {
        // call Service method

        return ResponseEntity
                .status(UserSuccessCode.USER_SIGN_IN_CREATED.getStatus())
                .body(ApiResponse.onComplete(UserSuccessCode.USER_SIGN_IN_CREATED, null));
    }

    @PostMapping("/my/pref")
    public ResponseEntity<ApiResponse<?>> createMyPrefFood(
            @Valid @RequestBody Object request
    ) {
        // call Service method

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.onComplete(UserSuccessCode.USER_PREF_FOOD_CREATED, null));
    }

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

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<?>> getMyPage(
            // JWT Security Holder에서 추출
    ) {
        // 서비스 메소드 호출
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.onComplete(UserSuccessCode.MY_PAGE_OK, null));
    }

    @PatchMapping("/my")
    public ResponseEntity<ApiResponse<?>> updateMyPage(
            // JWT Security Holder에서 추출
    ) {
        // 서비스 메소드 호출
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.onComplete(UserSuccessCode.MY_PAGE_OK, null));
    }

    @PostMapping("/auth/phone")
    public ResponseEntity<ApiResponse<?>> authPhoneNum(
            @Valid @RequestBody Object request
    ) {
        // 서비스 메소드 호출

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.onComplete(UserSuccessCode.AUTH_PHONE_NUM_OK, null));
    }

    @GetMapping("/my/alert")
    public ResponseEntity<ApiResponse<?>> getMyAlert(
            // JWT Security Context Holder
    ) {
        // call Service method

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.onComplete(UserSuccessCode.MY_ALERT_OK, null));
    }
    @GetMapping("/user/alert")
    public ResponseEntity<ApiResponse<?>> getMyAlertSetting(
            // JWT Security Context Holder
    ) {
        // call Service method

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.onComplete(UserSuccessCode.MY_ALERT_SETTING_OK, null));
    }
    @PatchMapping("/user/alert")
    public ResponseEntity<ApiResponse<?>> updateMyAlertSetting(
            // extract by JWT in Security Context Holder
    ) {
        // call Service method

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.onComplete(UserSuccessCode.MY_ALERT_SETTING_UPDATE_OK, null));
    }

    @DeleteMapping("/my")
    public ResponseEntity<ApiResponse<?>> deleteAccount(
            // extract by JWT in Security Context Holder
    ) {
        // call Service method

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.onComplete(UserSuccessCode.MY_ACCOUNT_DELETE_OK, null));
    }
}
