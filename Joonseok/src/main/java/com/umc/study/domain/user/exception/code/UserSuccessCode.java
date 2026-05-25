package com.umc.study.domain.user.exception.code;

import com.umc.study.global.apiPayload.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserSuccessCode implements BaseResponseCode {

    USER_SIGN_UP_CREATED(
            HttpStatus.CREATED,
            "USER201_1",
            "회원가입에 성공했습니다."
    ),
    USER_LOGIN_OK(
            HttpStatus.OK,
            "USER_200_1",
            "로그인에 성공했습니다."
    ),
    USER_PREF_FOOD_CREATED(
            HttpStatus.CREATED,
            "USER201_2",
            "회원님의 선호하는 음식 종류 저장에 성공했습니다."
    ),
    HOME_OK(HttpStatus.OK,
            "USER200_2",
            "홈 화면 조회에 성공했습니다."),
    MY_PAGE_OK(HttpStatus.OK,
            "USER200_3",
            "마이 페이지 조회에 성공했습니다."),
    AUTH_PHONE_NUM_OK(HttpStatus.OK,
            "USER200_4",
            "전화번호 인증에 성공했습니다."),
    MY_ALERT_OK(HttpStatus.OK,
            "USER200_5",
            "나의 알림 조회에 성공했습니다."),
    MY_ALERT_SETTING_OK(
            HttpStatus.OK,
            "USER200_6",
            "나의 알림 설정 조회에 성공했습니다."
    ),
    MY_ALERT_SETTING_UPDATE_OK(
            HttpStatus.OK,
            "USER200_7",
            "나의 알림 설정 수정에 성공했습니다."
    ),
    MY_ACCOUNT_DELETE_OK(
            HttpStatus.OK,
            "USER200_8",
            "내 계정 삭제에 성공했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
