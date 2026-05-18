package com.example.umc10thweek4.domain.mission.repository;

import com.example.umc10thweek4.domain.mission.entity.mapping.UserMission;
import com.example.umc10thweek4.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    Optional<UserMission> findByIdAndDeletedAtIsNull(Long id);

    List<UserMission> findByMemberIdAndStatusAndDeletedAtIsNull(
            Long memberId, MissionStatus status);

    // 홈 화면 - 내 미션 목록
    @Query("SELECT um FROM UserMission um " +
            "WHERE um.member.id = :memberId " +
            "AND um.deletedAt IS NULL")
    Page<UserMission> findMyMissions(
            @Param("memberId") Long memberId,
            Pageable pageable);

    boolean existsByMemberIdAndMissionIdAndDeletedAtIsNull(
            Long memberId, Long missionId);
}