package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResDTO.SignupRes signup(MemberReqDTO.SignupReq request) {
        // TODO: 회원가입 로직
        return null;
    }

    public MemberResDTO.HomeRes getHome() {
        // TODO: 홈화면 조회 로직
        return null;
    }

    public MemberResDTO.MissionListRes getMissions(String status, int page, int size) {
        // TODO: 미션 목록 조회 로직
        return null;
    }

    public MemberResDTO.MissionSuccessRes requestMissionSuccess(Long missionId) {
        // TODO: 미션 성공 요청 로직
        return null;
    }

    public MemberResDTO.MyInfoRes requestMyInfo(MemberReqDTO.MyInfoReq myInfoReq) {
        Long id = myInfoReq.id();
        Member member = memberRepository.findById(id).orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

}
