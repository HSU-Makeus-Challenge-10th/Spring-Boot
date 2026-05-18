package com.example.umc10thweek4.domain.member.service;

import com.example.umc10thweek4.domain.member.converter.MemberConverter;
import com.example.umc10thweek4.domain.member.dto.MemberReqDTO;
import com.example.umc10thweek4.domain.member.dto.MemberResDTO;
import com.example.umc10thweek4.domain.member.entity.Member;
import com.example.umc10thweek4.domain.member.entity.mapping.MemberFoodPreference;
import com.example.umc10thweek4.domain.member.entity.mapping.MemberNoticeSetting;
import com.example.umc10thweek4.domain.member.enums.Gender;
import com.example.umc10thweek4.domain.member.exception.MemberException;
import com.example.umc10thweek4.domain.member.exception.code.MemberErrorCode;
import com.example.umc10thweek4.domain.member.repository.MemberFoodPreferenceRepository;
import com.example.umc10thweek4.domain.member.repository.MemberNoticeSettingRepository;
import com.example.umc10thweek4.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberNoticeSettingRepository noticeSettingRepository;
    private final MemberFoodPreferenceRepository foodPreferenceRepository;

    /**
     * 회원가입
     */
    @Transactional
    public MemberResDTO.SignUp signUp(MemberReqDTO.SignUp request) {

        if (memberRepository.existsByEmail(request.email())) {
            throw new MemberException(MemberErrorCode.DUPLICATE_EMAIL);
        }

        if (memberRepository.existsByNickname(request.nickname())) {
            throw new MemberException(MemberErrorCode.DUPLICATE_NICKNAME);
        }

        Member member = memberRepository.save(
                Member.builder()
                        .name(request.name())
                        .nickname(request.nickname())
                        .email(request.email())
                        .password(request.password())   // TODO: 비밀번호 암호화
                        .birth(LocalDate.parse(request.birthday(),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .gender(Gender.valueOf(request.gender().toUpperCase()))
                        .phoneNumber(request.phoneNum())
                        .build()
        );

        // 알림 설정 기본값 저장
        noticeSettingRepository.save(
                MemberNoticeSetting.builder()
                        .member(member)
                        .getNewEvent(true)
                        .getReviewComment(true)
                        .getAskComment(true)
                        .build()
        );

        // 음식 선호 저장
        if (request.foodPreferences() != null && !request.foodPreferences().isEmpty()) {
            List<MemberFoodPreference> foodPreferences = request.foodPreferences().stream()
                    .map(food -> MemberFoodPreference.builder()
                            .member(member)
                            .food(food)
                            .build())
                    .toList();
            foodPreferenceRepository.saveAll(foodPreferences);
        }

        return MemberConverter.toSignUpRes(member);
    }

    /**
     * 마이페이지 조회
     */
    public MemberResDTO.GetInfo getMyPage(Long userId) {

        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        MemberNoticeSetting ns = noticeSettingRepository.findByMember(member)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOTICE_SETTING_NOT_FOUND));

        return MemberConverter.toGetInfoRes(member, ns);
    }
}