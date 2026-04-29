package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m WHERE m.store.town.id = :townId AND m.id > :cursor ORDER BY m.id ASC")
    List<Mission> findAvailableByTownCursor(@Param("townId") Long townId, @Param("cursor") Long cursor, Pageable pageable);

    @Query("SELECT m FROM Mission m WHERE m.id > :cursor ORDER BY m.id ASC")
    List<Mission> findAvailableCursor(@Param("cursor") Long cursor, Pageable pageable);

    @Query("SELECT m FROM Mission m WHERE m.store.id = :storeId AND m.id > :cursor ORDER BY m.id ASC")
    List<Mission> findByStoreIdCursor(@Param("storeId") Long storeId, @Param("cursor") Long cursor, Pageable pageable);
}
