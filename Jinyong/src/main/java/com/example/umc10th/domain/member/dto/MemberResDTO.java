package com.example.umc10th.domain.member.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

public class MemberResDTO {

    // 회원가입 응답
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO {
        Long userId;
        String nickname;
        String email;
    }

    // 홈 화면 응답
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeResultDTO {
        String nickname;
        Integer point;
        List<MissionDetailDTO> missions;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionDetailDTO {
        Long userMissionId;
        String missionTitle;
        Integer reward;
        String status;
    }
}
