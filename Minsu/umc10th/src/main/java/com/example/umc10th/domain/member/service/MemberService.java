package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.Term;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.FoodRepository;
import com.example.umc10th.domain.member.repository.MemberFoodRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.member.repository.MemberTermRepository;
import com.example.umc10th.domain.member.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final TermRepository termRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final MemberTermRepository memberTermRepository;

    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
        Member member = memberRepository.findById(dto.id())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    public MemberResDTO.PointInfo getPoints(String token) {
        Long memberId = 1L; // JWT 미구현 시 임시
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toPointInfo(member);
    }

    @Transactional
    public MemberResDTO.SignUpInfo signUp(MemberReqDTO.SignUp dto) {
        if (memberRepository.existsByEmail(dto.email())) {
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }

        Member member = Member.builder()
                .name(dto.name())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .detailAddress(dto.detailAddress())
                .gender(dto.gender() != null ? Gender.valueOf(dto.gender().toUpperCase()) : null)
                .birthDate(dto.birthDate() != null ? LocalDate.parse(dto.birthDate()) : null)
                .socialType(dto.socialType() != null ? SocialType.valueOf(dto.socialType().toUpperCase()) : null)
                .socialToken(dto.socialToken())
                .point(0)
                .build();

        memberRepository.save(member);

        if (dto.foodTypeIds() != null && !dto.foodTypeIds().isEmpty()) {
            List<Food> foods = foodRepository.findAllById(dto.foodTypeIds());
            if (foods.size() != dto.foodTypeIds().size()) {
                throw new MemberException(MemberErrorCode.FOOD_NOT_FOUND);
            }

            List<MemberFood> memberFoods = foods.stream()
                    .map(food -> MemberFood.builder().member(member).food(food).build())
                    .toList();
            memberFoodRepository.saveAll(memberFoods);
        }

        if (dto.termsIds() != null && !dto.termsIds().isEmpty()) {
            List<Term> terms = termRepository.findAllById(dto.termsIds());
            if (terms.size() != dto.termsIds().size()) {
                throw new MemberException(MemberErrorCode.TERM_NOT_FOUND);
            }

            List<MemberTerm> memberTerms = terms.stream()
                    .map(term -> MemberTerm.builder().member(member).term(term).agreedAt(LocalDateTime.now()).build())
                    .toList();
            memberTermRepository.saveAll(memberTerms);
        }

        return MemberConverter.toSignUpInfo(member);
    }
}
