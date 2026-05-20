package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.mapping.MemberMission;
import com.example.umc10th.domain.member.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    List<MemberMission> findAllByMember_IdAndStatus(Long memberId, MissionStatus missionStatus);
    Page<MemberMission> findAllByMember_IdAndStatus(Long memberId, MissionStatus missionStatus, Pageable pageable);
    Optional<MemberMission> findByMember_IdAndMission_Id(Long memberId, Long missionId);

    @Query("SELECT mm FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.status = :status")
    Page<MemberMission> findActiveMissions(@Param("memberId") Long memberId, @Param("status") MissionStatus status, Pageable pageable);


}
