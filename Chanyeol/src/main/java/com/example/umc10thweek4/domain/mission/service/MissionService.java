package com.example.umc10thweek4.domain.mission.service;

import com.example.umc10thweek4.domain.member.entity.Member;
import com.example.umc10thweek4.domain.member.repository.MemberRepository;
import com.example.umc10thweek4.domain.mission.converter.MissionConverter;
import com.example.umc10thweek4.domain.mission.dto.MissionReqDTO;
import com.example.umc10thweek4.domain.mission.dto.MissionResDTO;
import com.example.umc10thweek4.domain.mission.entity.Mission;
import com.example.umc10thweek4.domain.mission.entity.mapping.UserMission;
import com.example.umc10thweek4.domain.mission.enums.MissionStatus;
import com.example.umc10thweek4.domain.mission.exception.MissionException;
import com.example.umc10thweek4.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10thweek4.domain.mission.repository.MissionRepository;
import com.example.umc10thweek4.domain.mission.repository.UserMissionRepository;
import com.example.umc10thweek4.domain.store.entity.Store;
import com.example.umc10thweek4.domain.store.exception.StoreException;
import com.example.umc10thweek4.domain.store.exception.code.StoreErrorCode;
import com.example.umc10thweek4.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final StoreRepository storeRepository;
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
                .orElseThrow(() -> new MissionException(MissionErrorCode.MEMBER_NOT_FOUND));

        return MissionConverter.toHome(member, recommendedMissions);
    }

    /**
     * 내가 진행중 + 완료한 미션 목록
     */
    public MissionResDTO.Pagination<MissionResDTO.UserMissionList.UserMission> getMyMissions(
            Long memberId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {

        Sort sortInfo;
        if (sort != null && !sort.isBlank()) {
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();   // 기본: 최신순
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<UserMission> userMissionPage = userMissionRepository
                .findMyMissions(memberId, pageRequest);

        List<MissionResDTO.UserMissionList.UserMission> data = userMissionPage.getContent().stream()
                .map(MissionConverter::toUserMission)
                .toList();

        // Pagination DTO 생성
        return MissionConverter.toPagination(
                data,
                userMissionPage.getNumber(),
                userMissionPage.getSize()
        );
    }

    /**
     * 가게 내 미션 생성
     */
    @Transactional
    public Void createMission(Long storeId, MissionReqDTO.CreateMission dto) {
        // 가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 미션 생성
        Mission mission = MissionConverter.toMission(store, dto);

        // 미션 DB 저장
        missionRepository.save(mission);
        return null;
    }

    /**
     * 가게 내 미션 목록
     */
    public List<MissionResDTO.GetMission> getStoreMissions(Long storeId) {
        // 가게 미션들 조회
        List<Mission> missionList = missionRepository.findByStoreId(storeId);
        // 미션들 응답 DTO로 포장
        return missionList.stream()
                .map(MissionConverter::toGetStoreMission)
                .toList();
    }

    /**
     * 미션 참여 (UserMission 생성)
     */
    @Transactional
    public MissionResDTO.Participate participateMission(Long memberId, Long missionId) {
        Member member = memberRepository.findByIdAndDeletedAtIsNull(memberId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findByIdAndDeletedAtIsNull(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        // 이미 참여한 미션인지 체크
        if (userMissionRepository.existsByMemberIdAndMissionIdAndDeletedAtIsNull(memberId, missionId)) {
            throw new MissionException(MissionErrorCode.ALREADY_PARTICIPATED);
        }

        UserMission userMission = userMissionRepository.save(
                UserMission.builder()
                        .member(member)
                        .mission(mission)
                        .status(MissionStatus.IN_PROGRESS)
                        .build()
        );
        
        return MissionConverter.toParticipate(userMission);
    }

    /**
     * 미션 완료 처리
     */
    @Transactional
    public MissionResDTO.Complete completeMission(Long memberId, Long userMissionId, Integer recognizeAmount) {
        UserMission userMission = userMissionRepository.findByIdAndDeletedAtIsNull(userMissionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.USER_MISSION_NOT_FOUND));

        if (!userMission.getMember().getId().equals(memberId)) {
            throw new MissionException(MissionErrorCode.UNAUTHORIZED_MISSION);
        }

        // 미션 완료 처리
        userMission.completeMission(recognizeAmount);   // UserMission 엔티티에 메서드 추가 예정

        // 회원 포인트 지급
        Member member = userMission.getMember();
        member.addPoints(userMission.getMission().getMissionReward());

        return MissionConverter.toComplete(userMission);
    }
}