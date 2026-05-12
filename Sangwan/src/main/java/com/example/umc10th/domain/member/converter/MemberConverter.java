package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.RegionProgress;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.global.dto.CursorPageRes;
import com.example.umc10th.global.dto.OffsetPageRes;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static MemberResDTO.MyInfoRes toGetInfo(Member member) {
        return MemberResDTO.MyInfoRes.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getTotalPoint())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(null)
                .build();
    }

    public static MemberResDTO.HomeMissionItem toHomeMissionItem(Mission mission) {
        return MemberResDTO.HomeMissionItem.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .category(mission.getStore().getFoodCategory().getName())
                .content(mission.getContent())
                .reward(mission.getRewardPoint())
                .dDay(calculateDDay(mission))
                .build();
    }

    public static MemberResDTO.HomeRes toHomeRes(Member member, String regionName,
                                                  RegionProgress regionProgress,
                                                  List<Mission> availableMissions) {
        int currentCount = regionProgress != null && regionProgress.getSuccessCount() != null
                ? regionProgress.getSuccessCount() : 0;

        List<MemberResDTO.HomeMissionItem> missionItems = availableMissions.stream()
                .map(MemberConverter::toHomeMissionItem)
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

    public static MemberResDTO.MissionItem toMissionItem(MemberMission mm) {
        return MemberResDTO.MissionItem.builder()
                .memberMissionId(mm.getId())
                .missionId(mm.getMission().getId())
                .storeName(mm.getMission().getStore().getName())
                .content(mm.getMission().getContent())
                .reward(mm.getMission().getRewardPoint() + "P")
                .status(mm.getStatus().name())
                .rewardType("FIXED")
                .build();
    }

    public static CursorPageRes<MemberResDTO.MissionItem> toMissionListRes(
            List<MemberMission> missions, boolean hasNext, Long nextCursor,
            boolean isFirst, boolean isLast) {
        List<MemberResDTO.MissionItem> items = missions.stream()
                .map(MemberConverter::toMissionItem)
                .collect(Collectors.toList());

        return CursorPageRes.<MemberResDTO.MissionItem>builder()
                .list(items)
                .listSize(items.size())
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .isFirst(isFirst)
                .isLast(isLast)
                .build();
    }

    public static OffsetPageRes<MemberResDTO.MissionItem> toInProgressMissionPageRes(
            Page<MemberMission> page, int currentPage) {
        List<MemberResDTO.MissionItem> items = page.getContent().stream()
                .map(MemberConverter::toMissionItem)
                .collect(Collectors.toList());

        return OffsetPageRes.<MemberResDTO.MissionItem>builder()
                .list(items)
                .listSize(items.size())
                .currentPage(currentPage)
                .totalPage(page.getTotalPages())
                .totalCount(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    private static int calculateDDay(Mission mission) {
        if (mission.getEndAt() != null) {
            return (int) ChronoUnit.DAYS.between(LocalDateTime.now(), mission.getEndAt());
        }
        return mission.getDeadline() != null ? mission.getDeadline() : 0;
    }
}
