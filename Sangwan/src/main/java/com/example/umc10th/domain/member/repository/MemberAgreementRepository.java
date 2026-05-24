package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.mapping.MemberAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAgreementRepository extends JpaRepository<MemberAgreement, Long> {
}
