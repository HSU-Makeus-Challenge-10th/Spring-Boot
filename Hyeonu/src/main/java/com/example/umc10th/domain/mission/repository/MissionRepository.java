package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    // 특정 지역(Region)의 가게들에 걸린 미션 목록 조회
    @Query("SELECT m FROM Mission m JOIN m.store s WHERE s.region.id = :regionId")
    Page<Mission> findAllByRegionId(Long regionId, Pageable pageable);
}