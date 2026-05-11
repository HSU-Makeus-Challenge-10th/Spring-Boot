package com.example.umc10th.domain.store.repository;

import com.example.umc10th.domain.store.entity.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.store.id = :storeId")
    Double findAvgRatingByStoreId(@Param("storeId") Long storeId);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.store.id = :storeId")
    Long countReviewsByStoreId(@Param("storeId") Long storeId);

    @Query("""
            SELECT s FROM Store s
            JOIN FETCH s.town t
            WHERE t.id = :townId
              AND s.foodTypeId = :foodTypeId
              AND s.id > :cursor
            ORDER BY s.id ASC
            """)
    List<Store> findByTownAndFoodTypeCursor(
            @Param("townId") Long townId,
            @Param("foodTypeId") Long foodTypeId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
}
