package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.ActivatedMission;
import com.example.umc10th.domain.mission.enums.MissionState;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivatedMissionRepository extends JpaRepository<ActivatedMission, Long> {

    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);

    @Query("SELECT am FROM ActivatedMission am WHERE am.member.id = :memberId AND am.state = :state AND am.id > :cursor ORDER BY am.id ASC")
    List<ActivatedMission> findByMemberIdAndStateCursor(@Param("memberId") Long memberId, @Param("state") MissionState state, @Param("cursor") Long cursor, Pageable pageable);

    @Query("""
            SELECT am FROM ActivatedMission am
            JOIN FETCH am.mission m
            JOIN FETCH m.store
            WHERE am.member.id = :memberId
              AND am.state = :state
              AND am.id > :cursor
            ORDER BY am.id ASC
            """)
    List<ActivatedMission> findByMemberIdAndStateWithDetailsCursor(
            @Param("memberId") Long memberId,
            @Param("state") MissionState state,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    @Query("SELECT COUNT(am) FROM ActivatedMission am WHERE am.mission.store.town.id = :townId AND am.state = 'ONGOING'")
    Long countOngoingByTownId(@Param("townId") Long townId);
}
