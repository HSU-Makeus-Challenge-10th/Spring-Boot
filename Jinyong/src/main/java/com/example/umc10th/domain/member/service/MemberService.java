package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResDTO.GetInfo getInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetInfo(member);
    }

    public MemberResDTO.SignUp signUp(MemberReqDTO.SignUp request) {
        memberRepository.findByEmail(request.email())
                .ifPresent(member -> {
                    throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
                });

        String encodedPassword = passwordEncoder.encode(request.password());
        Member member = MemberConverter.toMember(request, encodedPassword);
        Member savedMember = memberRepository.save(member);

        return MemberConverter.toSignUp(savedMember);
    }
}
