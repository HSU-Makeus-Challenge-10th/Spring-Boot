package com.example.umc10thweek4.domain.member.service;

import com.example.umc10thweek4.domain.member.dto.MemberReqDTO;
import com.example.umc10thweek4.domain.member.dto.MemberResDTO;
import com.example.umc10thweek4.domain.member.entity.Member;
import com.example.umc10thweek4.domain.member.entity.mapping.MemberFoodPreference;
import com.example.umc10thweek4.domain.member.entity.mapping.MemberNoticeSetting;
import com.example.umc10thweek4.domain.member.enums.Gender;
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
            throw new RuntimeException("이미 사용 중인 이메일입니다.");
        }

        if (memberRepository.existsByNickname(request.nickname())) {
            throw new RuntimeException("이미 사용 중인 닉네임입니다.");
        }

        Member member = memberRepository.save(
                Member.builder()
                        .name(request.name())
                        .nickname(request.nickname())
                        .email(request.email())
                        .password(request.password())   // 실무에서는 암호화 필수!
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

        return new MemberResDTO.SignUp(
                member.getId(),
                member.getNickname()
        );
    }

    /**
     * 마이페이지 조회
     */
    public MemberResDTO.GetInfo getMyPage(Long userId) {

        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        MemberNoticeSetting ns = noticeSettingRepository.findByMember(member)
                .orElseThrow(() -> new RuntimeException("알림 설정 정보가 없습니다."));

        return MemberResDTO.GetInfo.builder()
                .userId(member.getId())
                .nickname(member.getNickname())
                .phone(member.getPhoneNumber())
                .totalPoints(member.getTotalPoints() != null
                        ? member.getTotalPoints().intValue() : 0)
                .noticeSettings(new MemberResDTO.GetInfo.NoticeSetting(
                        ns.getGetNewEvent(),
                        ns.getGetReviewComment(),
                        ns.getGetAskComment()
                ))
                .build();
    }
}