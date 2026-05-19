package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.ActivatedMission;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.MissionState;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.ActivatedMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.global.converter.PaginationConverter;
import com.example.umc10th.global.dto.CommonResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final ActivatedMissionRepository activatedMissionRepository;
    private final MemberRepository memberRepository;

    private static final Long TEMP_MEMBER_ID = 1L;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public MissionResDTO.MissionPageResult getCompletedMissions(Long cursor, int limit) {
        List<ActivatedMission> list = activatedMissionRepository
                .findByMemberIdAndStateWithDetailsCursor(TEMP_MEMBER_ID, MissionState.COMPLETED, cursor, PageRequest.of(0, limit));
        return MissionConverter.toMissionPageResult(list, limit);
    }

    public MissionResDTO.MissionPageResult getOngoingMissions(Long cursor, int limit) {
        List<ActivatedMission> list = activatedMissionRepository
                .findByMemberIdAndStateWithDetailsCursor(TEMP_MEMBER_ID, MissionState.ONGOING, cursor, PageRequest.of(0, limit));
        return MissionConverter.toMissionPageResult(list, limit);
    }

    public CommonResDTO.OffsetPagination<MissionResDTO.MissionItem> getOngoingMissions(
            MissionReqDTO.GetOngoingMissions dto
    ) {
        memberRepository.findById(dto.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        int size = dto.size() == null ? 10 : dto.size();
        PageRequest pageRequest = PageRequest.of(dto.page(), size, Sort.by("id").descending());
        Page<ActivatedMission> page = activatedMissionRepository
                .findAllByMember_IdAndState(dto.memberId(), dto.state(), pageRequest);
        List<MissionResDTO.MissionItem> data = page.getContent().stream()
                .map(MissionConverter::toMissionItem)
                .toList();

        return PaginationConverter.toOffsetPagination(page, data);
    }

    public MissionResDTO.AvailableMissionPageResult getAvailableMissions(Long townId, Long cursor, int limit) {
        List<Mission> list;
        if (townId != null) {
            list = missionRepository.findAvailableByTownCursorWithStore(townId, cursor, PageRequest.of(0, limit));
        } else {
            list = missionRepository.findAvailableCursorWithStore(cursor, PageRequest.of(0, limit));
        }
        return MissionConverter.toAvailableMissionPageResult(list, limit);
    }

    @Transactional
    public MissionResDTO.ActivatedMissionInfo startMission(Long missionId) {
        Member member = memberRepository.findById(TEMP_MEMBER_ID)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        if (activatedMissionRepository.existsByMemberIdAndMissionId(TEMP_MEMBER_ID, missionId)) {
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_STARTED);
        }

        String approverCode = generateApproverCode();

        ActivatedMission am = ActivatedMission.builder()
                .member(member)
                .mission(mission)
                .state(MissionState.ONGOING)
                .startedAt(LocalDateTime.now())
                .approverCode(approverCode)
                .build();

        activatedMissionRepository.save(am);
        return MissionConverter.toActivatedMissionInfo(am);
    }

    @Transactional
    public MissionResDTO.ActivatedMissionInfo successMission(Long activatedMissionId, MissionReqDTO.SuccessRequest dto) {
        ActivatedMission am = activatedMissionRepository.findById(activatedMissionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.ACTIVATED_MISSION_NOT_FOUND));

        if (!am.getApproverCode().equals(dto.approverCode())) {
            throw new MissionException(MissionErrorCode.INVALID_APPROVER_CODE);
        }

        am.complete(); // Dirty Checking: 트랜잭션 종료 시 변경된 state/completedAt이 UPDATE 된다.
        return MissionConverter.toActivatedMissionInfo(am);
    }

    public MissionResDTO.ApproverCodeInfo getApproverCode(Long activatedMissionId) {
        ActivatedMission am = activatedMissionRepository.findById(activatedMissionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.ACTIVATED_MISSION_NOT_FOUND));
        return MissionResDTO.ApproverCodeInfo.builder()
                .approverCode(am.getApproverCode())
                .build();
    }

    private String generateApproverCode() {
        return String.format("%06d", SECURE_RANDOM.nextInt(1_000_000));
    }
}
