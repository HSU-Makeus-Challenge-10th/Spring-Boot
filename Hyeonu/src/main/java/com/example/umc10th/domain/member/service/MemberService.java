package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    // 마이 페이지
    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
        // DTO에서 유저 ID를 추출
        Long memberId=dto.id();
        // DB에서 해당 유저 ID로 데이터 조회
        Member member=memberRepository.findById(memberId)
                .orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        // 컨버터를 이용해서 응답 DTO 생성 & return
        return MemberConverter.toGetInfo(member);
    }

    // 회원가입
    public MemberResDTO.SignUpResDTO signUp(MemberReqDTO.SignUpReqDTO dto) {
        // 비밀번호 BCrypt 암호화
        String encodedPassword=passwordEncoder.encode(dto.password());

        // DTO -> Entity 변환
        Member member=MemberConverter.toMember(dto, encodedPassword);

        // DB저장
        Member savedMember=memberRepository.save(member);

        // Entity -> ResponseDTO 변환
        return MemberConverter.toSignUpResDTO(savedMember);


    }
}
