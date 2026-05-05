package com.example.umc10thweek4.domain.member.repository;

import com.example.umc10thweek4.domain.member.entity.Member;
import com.example.umc10thweek4.domain.member.entity.mapping.MemberNoticeSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberNoticeSettingRepository extends JpaRepository<MemberNoticeSetting, Long> {

    Optional<MemberNoticeSetting> findByMember(Member member);
}