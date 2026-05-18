package com.example.umc10thweek4.domain.mission.repository;

import com.example.umc10thweek4.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    Optional<Mission> findByIdAndDeletedAtIsNull(Long id);

    // 마감 임박 추천 미션
    List<Mission> findTop5ByMissionDeadlineAfterAndDeletedAtIsNullOrderByMissionDeadlineAsc(
            LocalDateTime now);

    // 특정 가게의 활성 미션
    List<Mission> findByStoreId(Long storeId);

    // 지역별 활성 미션
    @Query("SELECT m FROM Mission m " +
            "JOIN m.store s " +
            "WHERE s.region.id = :regionId " +
            "AND m.missionDeadline > :now " +
            "AND m.deletedAt IS NULL")
    List<Mission> findActiveMissionsByRegion(
            @Param("regionId") Long regionId,
            @Param("now") LocalDateTime now);
}