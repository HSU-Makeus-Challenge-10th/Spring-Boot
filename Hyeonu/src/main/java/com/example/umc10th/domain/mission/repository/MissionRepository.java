package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m From Mission m "+
    "JOIN FETCH m.store s "+
    "JOIN s.region r "+
    "WHERE r.id=:regionId "+
    "AND m.status='READY'")
    Page<Mission> findAllByRegionId(@Param("regionId")Long regionId, Pageable pageable);
}