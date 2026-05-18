package com.example.umc10thweek4.domain.ask.service;

import com.example.umc10thweek4.domain.ask.converter.AskConverter;
import com.example.umc10thweek4.domain.ask.dto.AskReqDTO;
import com.example.umc10thweek4.domain.ask.dto.AskResDTO;
import com.example.umc10thweek4.domain.ask.entity.Ask;
import com.example.umc10thweek4.domain.ask.entity.AskImage;
import com.example.umc10thweek4.domain.ask.entity.mapping.AskReply;
import com.example.umc10thweek4.domain.ask.enums.AskType;
import com.example.umc10thweek4.domain.ask.exception.AskException;
import com.example.umc10thweek4.domain.ask.exception.code.AskErrorCode;
import com.example.umc10thweek4.domain.ask.repository.AskRepository;
import com.example.umc10thweek4.domain.member.entity.Member;
import com.example.umc10thweek4.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AskService {

    private final AskRepository askRepository;
    private final MemberRepository memberRepository;

    /**
     * 문의 등록
     */
    @Transactional
    public AskResDTO.Create createAsk(Long memberId, AskReqDTO.Create request) {

        Member member = memberRepository.findByIdAndDeletedAtIsNull(memberId)
                .orElseThrow(() -> new AskException(AskErrorCode.MEMBER_NOT_FOUND));

        Ask ask = Ask.builder()
                .member(member)
                .askTitle(request.askTitle())
                .askDetail(request.askDetail())
                .askType(AskType.valueOf(request.askType().toUpperCase()))
                .status("PENDING")
                .askTime(LocalDateTime.now())
                .build();

        Ask savedAsk = askRepository.save(ask);

        // 이미지 저장 (여러 장 가능)
        if (request.imageUrls() != null && !request.imageUrls().isEmpty()) {
            for (String imageUrl : request.imageUrls()) {
                AskImage askImage = AskImage.builder()
                        .ask(savedAsk)
                        .imageUrl(imageUrl)
                        .build();
                // askImageRepository.save(askImage); → 추후 Repository 추가
            }
        }

        return AskConverter.toCreateRes(savedAsk);
    }

    /**
     * 내가 작성한 문의 목록
     */
    public List<AskResDTO.GetList> getMyAsks(Long memberId) {
        List<Ask> asks = askRepository.findByMemberIdAndDeletedAtIsNullOrderByCreatedAtDesc(memberId);

        return asks.stream()
                .map(AskConverter::toGetListRes)
                .toList();
    }

    /**
     * 문의 상세 조회
     */
    public AskResDTO.GetDetail getAskDetail(Long askId) {
        Ask ask = askRepository.findDetailById(askId)
                .orElseThrow(() -> new AskException(AskErrorCode.ASK_NOT_FOUND));

        // 답변 있는 경우
        AskResDTO.GetDetail.AskReply reply = null; // TODO: AskReplyRepository에서 조회

        return AskConverter.toGetDetailRes(ask, List.of(), reply);
    }
}