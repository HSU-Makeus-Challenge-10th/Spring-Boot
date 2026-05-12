package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.MemberMission;
import com.example.umc10th.domain.member.enums.MissionStatus;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberMissionRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MemberResDTO.GetInfo getInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    //홈화면 (진행중인 미션 10개씩 페이징)
    public MemberResDTO.HomeResultDto getHome(Long memberId, int page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        Page<MemberMission> activeMissions = memberMissionRepository
                .findActiveMissions(memberId, MissionStatus.ACTIVE, PageRequest.of(page,10));
        return MemberConverter.toHomeResult(member, activeMissions);
    }
    // Mission  조회
    public List<MissionResDTO.MissionDto> getMissionsByStatus(
            Long memberId,
            MissionStatus status,
            Integer pageSize,
            Integer pageNum,
            String sort
    ) {
        Sort sortInfo;
        if (sort != null){
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, sortInfo);

        Page<MemberMission> memberMissions = memberMissionRepository
                .findAllByMember_IdAndStatus(memberId, status, pageRequest);
        List<Mission> missions = memberMissions.stream()
                .map(MemberMission::getMission)
                .collect(Collectors.toList());
        return MissionConverter.toMissionDtoList(missions);
    }
}
