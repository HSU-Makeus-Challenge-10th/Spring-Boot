package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.RegionProgress;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberAgreementRepository;
import com.example.umc10th.domain.member.repository.MemberFoodCategoryRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.member.repository.RegionProgressRepository;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MemberMissionStatus;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.store.entity.FoodCategory;
import com.example.umc10th.domain.store.repository.FoodCategoryRepository;
import com.example.umc10th.domain.term.entity.Term;
import com.example.umc10th.domain.term.repository.TermRepository;
import com.example.umc10th.global.dto.CursorPageRes;
import com.example.umc10th.global.dto.OffsetPageRes;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final RegionProgressRepository regionProgressRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final TermRepository termRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final MemberAgreementRepository memberAgreementRepository;
    private final MemberFoodCategoryRepository memberFoodCategoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Transactional
    public MemberResDTO.SignupRes signup(MemberReqDTO.SignupReq request) {
        if (memberRepository.existsByEmail(request.email())) {
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }

        Map<Long, Boolean> agreementByTermId = toAgreementMap(request.termAgreements());
        validateRequiredTerms(agreementByTermId);
        List<Term> terms = findTerms(agreementByTermId.keySet());
        List<FoodCategory> foodCategories = findFoodCategories(request.foodCategoryIds());

        LocalDate birth = parseBirthday(request.birthday());
        String encodedPassword = passwordEncoder.encode(request.password());
        Member member = MemberConverter.toMember(request, encodedPassword, birth);
        Member savedMember = memberRepository.save(member);

        saveMemberAgreements(savedMember, terms, agreementByTermId);
        saveMemberFoodCategories(savedMember, foodCategories);

        return MemberConverter.toSignupRes(savedMember);
    }

    public MemberResDTO.Login login(MemberReqDTO.LoginReq request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );
            AuthMember member = (AuthMember) authentication.getPrincipal();

            return MemberConverter.toLogin(jwtUtil.createAccessToken(member));
        } catch (AuthenticationException e) {
            throw new MemberException(MemberErrorCode.INVALID_LOGIN);
        }
    }

    public MemberResDTO.HomeRes getHome(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        RegionProgress regionProgress = regionProgressRepository
                .findTopByMemberIdAndIsCompletedFalseOrderByCreatedAtDesc(memberId)
                .orElse(null);

        String regionName = regionProgress != null ? regionProgress.getRegion().getName() : "지역 없음";

        List<Mission> availableMissions = regionProgress != null
                ? missionRepository.findAvailableMissionsForMember(
                        regionProgress.getRegion().getId(), memberId)
                : List.of();

        return MemberConverter.toHomeRes(member, regionName, regionProgress, availableMissions);
    }

    public CursorPageRes<MemberResDTO.MissionItem> getMissions(Long memberId, String status, Long cursor, int size) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        MemberMissionStatus missionStatus = switch (status.toUpperCase()) {
            case "INPROGRESS" -> MemberMissionStatus.CHALLENGING;
            case "COMPLETE" -> MemberMissionStatus.SUCCESS;
            default -> throw new MissionException(MissionErrorCode.INVALID_MISSION_STATUS);
        };

        long effectiveCursor = cursor != null ? cursor : Long.MAX_VALUE;

        List<MemberMission> fetched = memberMissionRepository.findByMemberIdAndStatusWithCursor(
                memberId, missionStatus, effectiveCursor, PageRequest.of(0, size + 1));

        boolean hasNext = fetched.size() > size;
        List<MemberMission> missions = hasNext ? fetched.subList(0, size) : fetched;
        Long nextCursor = hasNext ? missions.get(missions.size() - 1).getId() : null;

        return MemberConverter.toMissionListRes(missions, hasNext, nextCursor, cursor == null, !hasNext);
    }

    public OffsetPageRes<MemberResDTO.MissionItem> getInProgressMissions(Long memberId, int page, int size) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Page<MemberMission> memberMissionPage = memberMissionRepository.findPageByMemberIdAndStatus(
                memberId, MemberMissionStatus.CHALLENGING, PageRequest.of(page, size));

        return MemberConverter.toInProgressMissionPageRes(memberMissionPage, page);
    }

    public MemberResDTO.MissionSuccessRes requestMissionSuccess(Long missionId) {
        // TODO: 미션 성공 요청 로직
        return null;
    }

    public MemberResDTO.MyInfoRes requestMyInfo(AuthMember authMember) {
        return MemberConverter.toGetInfo(authMember.getMember());
    }

    private LocalDate parseBirthday(String birthday) {
        try {
            return LocalDate.parse(birthday);
        } catch (DateTimeParseException e) {
            throw new MemberException(MemberErrorCode.INVALID_BIRTHDAY_FORMAT);
        }
    }

    private Map<Long, Boolean> toAgreementMap(List<MemberReqDTO.TermAgreementReq> termAgreements) {
        return termAgreements.stream()
                .collect(Collectors.toMap(
                        MemberReqDTO.TermAgreementReq::termId,
                        MemberReqDTO.TermAgreementReq::isAgreed,
                        (previous, current) -> current,
                        LinkedHashMap::new
                ));
    }

    private void validateRequiredTerms(Map<Long, Boolean> agreementByTermId) {
        boolean hasRequiredTermNotAgreed = termRepository.findByIsRequiredTrue().stream()
                .anyMatch(term -> !Boolean.TRUE.equals(agreementByTermId.get(term.getId())));

        if (hasRequiredTermNotAgreed) {
            throw new MemberException(MemberErrorCode.REQUIRED_TERM_NOT_AGREED);
        }
    }

    private List<Term> findTerms(Set<Long> termIds) {
        List<Term> terms = termRepository.findAllById(termIds);
        if (terms.size() != termIds.size()) {
            throw new MemberException(MemberErrorCode.TERM_NOT_FOUND);
        }

        Map<Long, Term> termById = terms.stream()
                .collect(Collectors.toMap(Term::getId, Function.identity()));

        return termIds.stream()
                .map(termById::get)
                .toList();
    }

    private List<FoodCategory> findFoodCategories(List<Long> foodCategoryIds) {
        Set<Long> distinctFoodCategoryIds = new LinkedHashSet<>(foodCategoryIds);
        List<FoodCategory> foodCategories = foodCategoryRepository.findAllById(distinctFoodCategoryIds);
        if (foodCategories.size() != distinctFoodCategoryIds.size()) {
            throw new MemberException(MemberErrorCode.FOOD_CATEGORY_NOT_FOUND);
        }

        Map<Long, FoodCategory> foodCategoryById = foodCategories.stream()
                .collect(Collectors.toMap(FoodCategory::getId, Function.identity()));

        return distinctFoodCategoryIds.stream()
                .map(foodCategoryById::get)
                .toList();
    }

    private void saveMemberAgreements(Member member, List<Term> terms, Map<Long, Boolean> agreementByTermId) {
        memberAgreementRepository.saveAll(terms.stream()
                .map(term -> MemberConverter.toMemberAgreement(member, term, agreementByTermId.get(term.getId())))
                .toList());
    }

    private void saveMemberFoodCategories(Member member, List<FoodCategory> foodCategories) {
        memberFoodCategoryRepository.saveAll(foodCategories.stream()
                .map(foodCategory -> MemberConverter.toMemberFoodCategory(member, foodCategory))
                .toList());
    }

}
