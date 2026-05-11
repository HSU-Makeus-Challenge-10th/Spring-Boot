package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.mapping.RegionProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionProgressRepository extends JpaRepository<RegionProgress, Long> {

    Optional<RegionProgress> findTopByMemberIdAndIsCompletedFalseOrderByCreatedAtDesc(Long memberId);
}
