package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m " +
            "JOIN FETCH m.store s " +
            "JOIN FETCH s.foodCategory " +
            "WHERE s.region.id = :regionId " +
            "AND m.status = true " +
            "AND m.id NOT IN (" +
            "  SELECT mm.mission.id FROM MemberMission mm WHERE mm.member.id = :memberId" +
            ")")
    List<Mission> findAvailableMissionsForMember(@Param("regionId") Long regionId,
                                                  @Param("memberId") Long memberId);
}
