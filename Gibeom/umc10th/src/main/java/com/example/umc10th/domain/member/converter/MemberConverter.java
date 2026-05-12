package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.MemberMission;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .userPoint(member.getUserPoint())
                .build();
    }

    public static MemberResDTO.HomeResultDto toHomeResult(Member member, Page<MemberMission> memberMissions) {
        List<MemberResDTO.HomeMissionDto> missionDtos = memberMissions.getContent().stream()
                .map(MemberConverter::toHomeMissionDto)
                .collect(Collectors.toList());

        return MemberResDTO.HomeResultDto.builder()
                .point(member.getUserPoint())
                .region(member.getRegion() != null ? member.getRegion().getRegionName() : null)
                .missions(missionDtos)
                .hasNext(memberMissions.hasNext())
                .build();
    }

    public static MemberResDTO.HomeMissionDto toHomeMissionDto(MemberMission memberMission) {
        return MemberResDTO.HomeMissionDto.builder()
                .missionId(memberMission.getMission().getId())
                .storeName(null)
                .storeCategory(null)
                .rewardPoint(memberMission.getMission().getPoint())
                .deadline(null)
                .status(memberMission.getStatus().name())
                .build();
    }
}
