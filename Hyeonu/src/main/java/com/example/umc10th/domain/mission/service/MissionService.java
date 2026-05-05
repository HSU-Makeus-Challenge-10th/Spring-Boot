package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MissionResDTO.MissionChallengeResult challengeMission(Long missionId, MissionReqDTO.ChallengeDTO request) {
        Member member = memberRepository.findById(request.memberId()).orElseThrow();
        Mission mission = missionRepository.findById(missionId).orElseThrow();

        // 도전 테이블 데이터 생성
        MemberMission memberMission = MissionConverter.toMemberMission(member, mission);
        return MissionConverter.toMissionChallengeResult(memberMissionRepository.save(memberMission));
    }

    public Page<MissionResDTO.MissionSummaryDTO> getMemberMissionList(Long memberId, MissionStatus status, Integer page) {
        Page<MemberMission> memberMissions = memberMissionRepository.findAllByMemberIdAndStatus(memberId, status, PageRequest.of(page, 10));
        return memberMissions.map(MissionConverter::toMissionSummaryDTO); // 변환 적용
    }

    public Page<MissionResDTO.MissionSummaryDTO> getMissionListByRegion(Long regionId, Integer page) {
        Page<Mission> missions = missionRepository.findAllByRegionId(regionId, PageRequest.of(page, 10));
        return missions.map(MissionConverter::toMissionSummaryDTO); // 변환 적용
    }
}
