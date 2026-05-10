package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    // 개별 미션 엔티티 -> 상세 DTO
    public static MissionResDTO.MissionDetailDTO toMissionDetailDTO(MemberMission memberMission){
        return MissionResDTO.MissionDetailDTO.builder()
                .storeName(memberMission.getMission().getStore().getName())
                .reward(memberMission.getMission().getRewardPoint())
                .missionSpec(memberMission.getMission().getContent())
                .status(memberMission.getStatus())
                .build();
    }

    // Page<MemberMission -> 전체 목록 DTO
    public static MissionResDTO.MissionListResDTO toMissionListDTO(Page<MemberMission> memberMissionPage) {
        // 상세 리스트로 변환
        List<MissionResDTO.MissionDetailDTO> missionDetailDTOList= memberMissionPage.getContent().stream()
                .map(MissionConverter::toMissionDetailDTO)
                .collect(Collectors.toList());

        // 페이징 정보와 함께 응답 객체 생성
        return MissionResDTO.MissionListResDTO.builder()
                .missionList(missionDetailDTOList)
                .listSize(missionDetailDTOList.size())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }
}