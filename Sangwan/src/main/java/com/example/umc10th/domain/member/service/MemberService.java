package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.RegionProgress;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.member.repository.RegionProgressRepository;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.UserMissionStatus;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final RegionProgressRepository regionProgressRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MemberResDTO.SignupRes signup(MemberReqDTO.SignupReq request) {
        // TODO: 회원가입 로직
        return null;
    }

    public MemberResDTO.HomeRes getHome(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        RegionProgress regionProgress = regionProgressRepository
                .findTopByMemberIdAndIsCompletedFalseOrderByCreatedAtDesc(memberId)
                .orElse(null);

        String regionName = regionProgress != null ? regionProgress.getRegion().getName() : "지역 없음";
        int currentCount = regionProgress != null && regionProgress.getSuccessCount() != null
                ? regionProgress.getSuccessCount() : 0;

        List<Mission> availableMissions = regionProgress != null
                ? missionRepository.findAvailableMissionsForMember(
                        regionProgress.getRegion().getId(), memberId)
                : List.of();

        List<MemberResDTO.HomeMissionItem> missionItems = availableMissions.stream()
                .map(mission -> MemberResDTO.HomeMissionItem.builder()
                        .missionId(mission.getId())
                        .storeName(mission.getStore().getName())
                        .category(mission.getStore().getFoodCategory().getName())
                        .content(mission.getContent())
                        .reward(mission.getRewardPoint())
                        .dDay(calculateDDay(mission))
                        .build())
                .collect(Collectors.toList());

        return MemberResDTO.HomeRes.builder()
                .memberPoint(member.getTotalPoint())
                .regionName(regionName)
                .missionProgress(MemberResDTO.MissionProgress.builder()
                        .currentCount(currentCount)
                        .targetCount(10)
                        .rewardPoint(1000)
                        .build())
                .missions(missionItems)
                .build();
    }

    public MemberResDTO.MissionListRes getMissions(Long memberId, String status, Long cursor, int size) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        UserMissionStatus missionStatus = switch (status.toUpperCase()) {
            case "INPROGRESS" -> UserMissionStatus.CHALLENGING;
            case "COMPLETE" -> UserMissionStatus.SUCCESS;
            default -> throw new MissionException(MissionErrorCode.INVALID_MISSION_STATUS);
        };

        long effectiveCursor = cursor != null ? cursor : Long.MAX_VALUE;

        List<MemberMission> fetched = memberMissionRepository.findByMemberIdAndStatusWithCursor(
                memberId, missionStatus, effectiveCursor, PageRequest.of(0, size + 1));

        boolean hasNext = fetched.size() > size;
        List<MemberMission> missions = hasNext ? fetched.subList(0, size) : fetched;

        Long nextCursor = hasNext ? missions.get(missions.size() - 1).getId() : null;

        List<MemberResDTO.MissionItem> missionItems = missions.stream()
                .map(mm -> MemberResDTO.MissionItem.builder()
                        .memberMissionId(mm.getId())
                        .missionId(mm.getMission().getId())
                        .storeName(mm.getMission().getStore().getName())
                        .content(mm.getMission().getContent())
                        .reward(mm.getMission().getRewardPoint() + "P")
                        .status(mm.getStatus().name())
                        .rewardType("FIXED")
                        .build())
                .collect(Collectors.toList());

        return MemberResDTO.MissionListRes.builder()
                .missionList(missionItems)
                .listSize(missionItems.size())
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .isFirst(cursor == null)
                .isLast(!hasNext)
                .build();
    }

    public MemberResDTO.MissionSuccessRes requestMissionSuccess(Long missionId) {
        // TODO: 미션 성공 요청 로직
        return null;
    }

    public MemberResDTO.MyInfoRes requestMyInfo(MemberReqDTO.MyInfoReq myInfoReq) {
        Member member = memberRepository.findById(myInfoReq.id())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    private int calculateDDay(Mission mission) {
        if (mission.getEndAt() != null) {
            return (int) ChronoUnit.DAYS.between(LocalDateTime.now(), mission.getEndAt());
        }
        return mission.getDeadline() != null ? mission.getDeadline() : 0;
    }
}
