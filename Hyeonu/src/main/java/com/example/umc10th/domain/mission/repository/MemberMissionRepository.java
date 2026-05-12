package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    // 특정 유저의 상태별 미션 목록 조회 (페이징 포함)
    @Query("SELECT mm FROM MemberMission mm " +
    "JOIN FETCH mm.mission m "+
    "JOIN FETCH m.store s "+
    "WHERE mm.member=:member AND mm.status = :status")
    Page<MemberMission> findAllByMemberIdAndStatus(
            @Param("member")Member member,
            @Param("status")MissionStatus status,
            Pageable pageable);
}
