package com.example.umc10th.domain.notification.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.notification.converter.NotificationConverter;
import com.example.umc10th.domain.notification.dto.NotificationReqDTO;
import com.example.umc10th.domain.notification.dto.NotificationResDTO;
import com.example.umc10th.domain.notification.entity.NotificationSettings;
import com.example.umc10th.domain.notification.repository.NotificationSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationService {

    private final NotificationSettingsRepository notificationSettingsRepository;
    private final MemberRepository memberRepository;

    public NotificationResDTO.Settings getSettings(Long memberId) {
        return notificationSettingsRepository.findByMemberId(memberId)
                .map(NotificationConverter::toSettings)
                .orElseGet(NotificationConverter::toDefaultSettings);
    }

    @Transactional
    public void updateSettings(Long memberId, NotificationReqDTO.Update dto) {
        if (dto == null) {
            return;
        }

        NotificationSettings settings = notificationSettingsRepository.findByMemberId(memberId)
                .orElseGet(() -> createSettings(memberId));
        settings.update(dto.eventEnabled(), dto.reviewReplyEnabled(), dto.inquiryReplyEnabled());
    }

    private NotificationSettings createSettings(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return notificationSettingsRepository.save(NotificationSettings.builder()
                .member(member)
                .build());
    }
}
