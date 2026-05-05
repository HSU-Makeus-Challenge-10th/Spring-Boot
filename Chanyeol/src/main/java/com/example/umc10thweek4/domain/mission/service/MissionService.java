package com.example.umc10thweek4.domain.mission.service;

import com.example.umc10thweek4.domain.member.entity.Member;
import com.example.umc10thweek4.domain.member.repository.MemberRepository;
import com.example.umc10thweek4.domain.mission.dto.MissionResDTO;
import com.example.umc10thweek4.domain.mission.entity.Mission;
import com.example.umc10thweek4.domain.mission.entity.mapping.UserMission;
import com.example.umc10thweek4.domain.mission.enums.MissionStatus;
import com.example.umc10thweek4.domain.mission.repository.MissionRepository;
import com.example.umc10thweek4.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final MemberRepository memberRepository;

    /**
     * 홈 화면 데이터 조회 (추천 미션 + 유저 정보)
     */
    public MissionResDTO.Home getHomeMissions(Long regionId, Long memberId) {
        LocalDateTime now = LocalDateTime.now();

        // 추천 미션 (마감 임박 순)
        List<Mission> recommendedMissions = missionRepository
                .findTop5ByMissionDeadlineAfterAndDeletedAtIsNullOrderByMissionDeadlineAsc(now);

        List<MissionResDTO.Home.RecommendedMission> recommendedList = recommendedMissions.stream()
                .map(m -> new MissionResDTO.Home.RecommendedMission(
                        m.getId(),
                        m.getStore().getStoreName(),
                        m.getStore().getCategory(),
                        Math.toIntExact(m.getMissionReward()),
                        m.getMissionDeadline().toString(),
                        m.getTargetAmount()
                ))
                .toList();

        // 회원 정보 조회
        Member member = memberRepository.findByIdAndDeletedAtIsNull(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        return new MissionResDTO.Home(
                "안암동",                    // TODO: 실제 지역 연동
                Math.toIntExact(member.getTotalPoints()),
                0,                           // TODO: 진행중 미션 수
                member.getCompleteNum(),
                5000,                        // TODO: 목표 보상 (추후 설계)
                recommendedList,
                false                        // unreadNotice (추후 NoticeService 연동)
        );
    }

    /**
     * 내가 진행중 + 완료한 미션 목록
     */
    public MissionResDTO.UserMissionList getMyMissions(Long memberId) {
        List<UserMission> userMissions = userMissionRepository
                .findMyMissions(memberId);

        List<MissionResDTO.UserMissionList.UserMission> missionList = userMissions.stream()
                .map(um -> new MissionResDTO.UserMissionList.UserMission(
                        um.getId(),
                        um.getMission().getId(),
                        um.getMission().getMissionTitle(),
                        um.getMission().getStore().getStoreName(),
                        0,                          // recognizeAmount (인증 금액)
                        um.getStatus(),
                        Math.toIntExact(um.getMission().getMissionReward())
                ))
                .toList();

        return new MissionResDTO.UserMissionList(missionList);
    }

    /**
     * 미션 참여 (UserMission 생성)
     */
    @Transactional
    public UserMission participateMission(Long memberId, Long missionId) {
        Member member = memberRepository.findByIdAndDeletedAtIsNull(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Mission mission = missionRepository.findByIdAndDeletedAtIsNull(missionId)
                .orElseThrow(() -> new RuntimeException("Mission not found"));

        // 이미 참여한 미션인지 체크 (추후 커스텀 예외로 변경)
        boolean alreadyParticipated = userMissionRepository
                .existsByMemberIdAndMissionIdAndDeletedAtIsNull(memberId, missionId);

        if (alreadyParticipated) {
            throw new RuntimeException("이미 참여한 미션입니다.");
        }

        UserMission userMission = UserMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.IN_PROGRESS)
                .build();

        return userMissionRepository.save(userMission);
    }

    /**
     * 미션 완료 처리
     */
    @Transactional
    public UserMission completeMission(Long memberId, Long userMissionId, Integer recognizeAmount) {
        UserMission userMission = userMissionRepository.findByIdAndDeletedAtIsNull(userMissionId)
                .orElseThrow(() -> new RuntimeException("UserMission not found"));

        if (!userMission.getMember().getId().equals(memberId)) {
            throw new RuntimeException("권한이 없습니다.");
        }

        // 미션 완료 처리
        userMission.completeMission(recognizeAmount);   // UserMission 엔티티에 메서드 추가 예정

        // 회원 포인트 지급
        Member member = userMission.getMember();
        member.addPoints(userMission.getMission().getMissionReward());

        return userMission;
    }
}