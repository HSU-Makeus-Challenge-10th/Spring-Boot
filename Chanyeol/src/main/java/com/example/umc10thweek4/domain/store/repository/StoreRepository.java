package com.example.umc10thweek4.domain.store.repository;

import com.example.umc10thweek4.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findByIdAndDeletedAtIsNull(Long id);

    List<Store> findByRegionIdAndDeletedAtIsNull(Long regionId);

    // 지역 + 카테고리 필터 (Soft Delete 적용)
    @Query("SELECT s FROM Store s " +
            "WHERE s.region.id = :regionId " +
            "AND s.deletedAt IS NULL " +
            "AND (:category IS NULL OR s.category = :category)")
    List<Store> findByRegionIdAndCategory(
            @Param("regionId") Long regionId,
            @Param("category") String category);
}