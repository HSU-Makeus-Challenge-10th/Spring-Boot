package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.MemberMissionStatus;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    public void createMission(
            Long storeId,
            MissionReqDTO.CreateMission dto
    ) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(store, dto);
        missionRepository.save(mission);
    }

    @Transactional
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissionList(
            Long storeId,
            Integer pageNumber,
            Integer pageSize,
            String sort
    ) {
        storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        return getMissions(storeId, pageSize, pageNumber, sort);
    }

    // 내가 진행 중인 미션 조회
    @Transactional
    public MissionResDTO.Pagination<MissionResDTO.MyOngoingMission> getMyOngoingMissions(
            Long memberId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {
        Sort sortInfo;

        if (sort != null) {
            sortInfo = Sort.by(sort).descending();
        } else {
            sortInfo = Sort.by("id").descending();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<MemberMission> memberMissionPage =
                memberMissionRepository.findMyOngoingMissions(
                        memberId,
                        MemberMissionStatus.ONGOING,
                        pageRequest
                );

        return MissionConverter.toPagination(
                memberMissionPage.map(MissionConverter::toMyOngoingMission).toList(),
                memberMissionPage.getNumber(),
                memberMissionPage.getSize()
        );
    }

    // 가게 내 미션들 조회
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissions(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {

        // 정렬 정보 생성
        Sort sortInfo;
        if (sort != null) {
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }

        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        // 가게 내 미션들 조회
        Page<Mission> missionList = missionRepository.findMissionByStoreId(storeId, pageRequest);

        // 미션들 응답 DTO로 포장하기
        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }
}
