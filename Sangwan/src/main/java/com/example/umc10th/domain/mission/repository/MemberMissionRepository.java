package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.UserMissionStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE mm.member.id = :memberId " +
            "AND mm.status = :status " +
            "AND mm.id < :cursor " +
            "ORDER BY mm.id DESC")
    List<MemberMission> findByMemberIdAndStatusWithCursor(@Param("memberId") Long memberId,
                                                           @Param("status") UserMissionStatus status,
                                                           @Param("cursor") Long cursor,
                                                           Pageable pageable);

    Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);

    @Query(value = "SELECT mm FROM MemberMission mm JOIN FETCH mm.mission m JOIN FETCH m.store s " +
            "WHERE mm.member.id = :memberId AND mm.status = :status",
            countQuery = "SELECT COUNT(mm) FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.status = :status")
    Page<MemberMission> findPageByMemberIdAndStatus(@Param("memberId") Long memberId,
                                                    @Param("status") UserMissionStatus status,
                                                    Pageable pageable);
}
