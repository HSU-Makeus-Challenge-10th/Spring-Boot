package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
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
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    public MissionResDTO.MissionListResDTO getMemberMissionList(Long memberId, MissionReqDTO.MissionListReqDTO dto) {

        // DB에서 해당 유저 ID로 데이터 조회
        Member member=memberRepository.findById(memberId)
                .orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        // 몇 번재 페이지를, 몇 개씩 가져올지
        PageRequest pageRequest=PageRequest.of(dto.page(),10);

        // DB에서 해당 멤버와 상태가 일치하는 데이터를 페이징해서 가져옴
        Page<MemberMission> memberMissionPage=memberMissionRepository.findAllByMemberIdAndStatus(
                member,
                dto.status(), // 진행중/완료 선택한 데이터
                pageRequest
        );
        // 컨버터를 이용해서 응답 DTO 생성 & return
        return MissionConverter.toMissionListDTO(memberMissionPage);
    }
    }

