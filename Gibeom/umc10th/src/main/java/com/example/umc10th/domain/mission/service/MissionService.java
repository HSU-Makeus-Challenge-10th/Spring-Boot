package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.MemberMission;
import com.example.umc10th.domain.member.enums.MissionStatus;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.member.repository.MemberMissionRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    //완료된 미션
    @Transactional
    public ReviewResDTO.WriteReviewResultDto writeReview(Long memberId, Long missionId, ReviewReqDTO.WriteReviewDto dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        MemberMission memberMission = memberMissionRepository
                .findByMember_IdAndMission_Id(memberId, missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        if (memberMission.getStatus() != MissionStatus.COMPLETED) {
            throw new MissionException(MissionErrorCode.NOT_COMPLETED);
        }

        if (memberMission.getReview() != null) {
            throw new ReviewException(ReviewErrorCode.ALREADY_REVIEWED);
        }

        Review review = ReviewConverter.toReview(dto, member, memberMission);
        Review savedReview = reviewRepository.save(review);
        return ReviewConverter.toWriteReviewResult(savedReview);
    }

    //가게 미션 생성
    @Transactional
    public Void createMission(
            Long storeId,
            MissionReqDTO.CreateMission dto
    ){
        //가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));
        //미션 생성
        Mission mission = MissionConverter.toMission(store, dto);

        //미션 DB에 미션 저장
        missionRepository.save(mission);
        return null;
    }

    //가게 미션 조회
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissions(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ){
        //정렬정보 생성
        Sort sortInfo;
        if (sort != null){
            sortInfo = Sort.by(sort);
        }  else {
            sortInfo = Sort.by("id").descending();
        }

        //페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        //가게 내 미션들 조회
        Page<Mission> missionList = missionRepository.findAllByStore_Id(storeId, pageRequest);

        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }

    //유저가 진행중인 미션 조회하기
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMemberMissions(
            Long memberId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ){
        // 정렬 정보
        Sort sortInfo;
        if (sort != null){
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }

        //페이지 정보 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        //내 미션들 조회 {memberId}
        Page<Mission> missionList = missionRepository.findAllByMember_Id(memberId, pageRequest);
        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }


}
