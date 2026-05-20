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
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.store.entity.Region;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.RegionRepository;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    public MissionResDTO.MissionListResDTO getMemberMissionList(MissionReqDTO.MissionListReqDTO dto) {

        // DB에서 해당 유저 ID로 데이터 조회
        Member member=memberRepository.findById(dto.memberId())
                .orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 몇 번재 페이지를, 몇 개씩 가져올지
        PageRequest pageRequest=PageRequest.of(dto.page(),3);

        // DB에서 해당 멤버와 상태가 일치하는 데이터를 페이징해서 가져옴
        Page<MemberMission> memberMissionPage=memberMissionRepository.findAllByMemberIdAndStatus(
                member,
                dto.status(), // 진행중/완료 선택한 데이터
                pageRequest
        );
        // 컨버터를 이용해서 응답 DTO 생성 & return
        return MissionConverter.toMissionListDTO(memberMissionPage);
    }

    public MissionResDTO.RegionMissionListDTO getRegionMissionList(Long regionId, MissionReqDTO.MyMissionReqDTO dto) {

        // 지역 존재 확인
        Region region=regionRepository.findById(regionId)
                .orElseThrow(()->new StoreException(StoreErrorCode.REGION_NOT_FOUND));

        // 페이징
        PageRequest pageRequest=PageRequest.of(dto.page(),3);

        // 리포지토리에서 지역 id에 해당하는 미션 조회
        Page<Mission> missionPage=missionRepository.findAllByRegionId(regionId,pageRequest);

        // 컨버터를 이용해서 응답 dto로 변환 후 반환
        return MissionConverter.toRegionMissionListDTO(missionPage);
    }

    @Transactional
    public Void createMission(Long storeId, MissionReqDTO.CreateMission dto) {

        // 가게 찾기
        Store store=storeRepository.findById(storeId)
                .orElseThrow(()->new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 미션 생성
        Mission mission= MissionConverter.toMission(store,dto);

        // 미션 DB 저장
        missionRepository.save(mission);
        return null;
    }

    // 가게 내 미션들 조회
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissions(
            Long storeId,
            Integer pageSize,
            String cursor,
            String query
    ) {
        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest=PageRequest.of(0,pageSize);

        long idCursor;
        Slice<Mission> missionList;
        String nextCursor;

        // 커서가 있는 경우
        if(!cursor.equals("-1")) {

            // 커서 분리
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()) {
                case "id":

                    // 커서 타입 변환
                    Long prevCursor = Long.parseLong(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);

                    // 가게 내 미션들 조회& where절에 커서값 기입
                    missionList = missionRepository.findMissionByStore_IdAndIdLessThanOrderByIdDesc(
                            storeId,
                            idCursor,
                            pageRequest
                    );
                    break;
                default:
                    throw new MissionException(MissionErrorCode.QUERY_NOT_VALID);
            }
        }else{
            // 커서 없이 조회
            missionList=missionRepository.findMissionByStore_IdOrderByIdDesc(storeId,pageRequest);
    }
        // 다음 커서 계산
        nextCursor=missionList.getContent().getLast().getId()+":"+missionList.getContent().getLast().getId();

        // 미션들 응답 DTO로 포장하기
        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.hasNext(),
                nextCursor,
                missionList.getSize()
        );
    }
}

