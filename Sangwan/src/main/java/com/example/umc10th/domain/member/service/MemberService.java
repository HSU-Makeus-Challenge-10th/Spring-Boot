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
import com.example.umc10th.global.dto.CursorPageRes;
import com.example.umc10th.global.dto.OffsetPageRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        List<Mission> availableMissions = regionProgress != null
                ? missionRepository.findAvailableMissionsForMember(
                        regionProgress.getRegion().getId(), memberId)
                : List.of();

        return MemberConverter.toHomeRes(member, regionName, regionProgress, availableMissions);
    }

    public CursorPageRes<MemberResDTO.MissionItem> getMissions(Long memberId, String status, Long cursor, int size) {
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

        return MemberConverter.toMissionListRes(missions, hasNext, nextCursor, cursor == null, !hasNext);
    }

    public OffsetPageRes<MemberResDTO.MissionItem> getInProgressMissions(
            MemberReqDTO.GetInProgressMissionsReq request, int page, int size) {
        memberRepository.findById(request.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Page<MemberMission> memberMissionPage = memberMissionRepository.findPageByMemberIdAndStatus(
                request.memberId(), UserMissionStatus.CHALLENGING, PageRequest.of(page, size));

        return MemberConverter.toInProgressMissionPageRes(memberMissionPage, page);
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

}
