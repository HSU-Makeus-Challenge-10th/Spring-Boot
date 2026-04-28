package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    SIGNUP(HttpStatus.CREATED, "MEMBER_201", "반가워요! 회원 가입이 완료되었습니다."),
    HOME(HttpStatus.OK, "HOME_200", "홈 화면 조회가 완료되었습니다."),
    MISSION_LIST(HttpStatus.OK, "MISSION_200", "미션 목록 조회가 완료되었습니다."),
    MISSION_SUCCESS_REQUEST(HttpStatus.OK, "MISSION_200", "미션 성공 검토 요청이 완료되었습니다."),
    MYINFO(HttpStatus.OK, "MEMBER_201", "성공적으로 멤버를 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
