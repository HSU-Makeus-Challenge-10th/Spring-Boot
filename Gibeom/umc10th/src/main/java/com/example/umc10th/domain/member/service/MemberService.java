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
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public MemberResDTO.GetInfo getInfo(
            AuthMember member
    ) {
        Member User = member.getMember();
        memberRepository.findByEmail(User.getEmail())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        return MemberConverter.toGetInfo(User);
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

    public MemberResDTO.LoginResult login(MemberReqDTO.Login req) {
        Member member = memberRepository.findByEmail(req.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        if (!passwordEncoder.matches(req.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }
        String accessToken = jwtUtil.createAccessToken(new AuthMember(member));
        return MemberConverter.toLoginResult(accessToken);
    }

    public void signUp(MemberReqDTO.SignUp req) {
        //닉네임 혹은 이메일이 이미 존재할 때
        if(memberRepository.existsByEmail(req.email())){
            throw new MemberException(MemberErrorCode.EMAIL_DUPLICATED);
        } else if (memberRepository.existsByNickname(req.nickname())){
            throw new MemberException(MemberErrorCode.NICKNAME_DUPLICATED);
        }

        String encodedPassword = passwordEncoder.encode(req.password());
        Member member = MemberConverter.toMember(req, encodedPassword);
        memberRepository.save(member);
    }
}
