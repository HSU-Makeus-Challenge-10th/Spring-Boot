package com.example.umc10thweek4.domain.region.repository;

import com.example.umc10thweek4.domain.region.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {

    // 활성화된 모든 지역 조회
    List<Region> findAllByDeletedAtIsNullOrderByRegionNameAsc();

    // 특정 지역 조회 (Soft Delete 적용)
    Optional<Region> findByIdAndDeletedAtIsNull(Long id);

    // @Query 방식 예시
    @Query("SELECT r FROM Region r WHERE r.deletedAt IS NULL ORDER BY r.regionName ASC")
    List<Region> findAllActiveRegions();
}