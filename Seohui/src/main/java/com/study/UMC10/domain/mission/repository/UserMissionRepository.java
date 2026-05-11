package com.study.UMC10.domain.mission.repository;

import com.study.UMC10.domain.mission.entity.UserMission;
import com.study.UMC10.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query("SELECT um FROM UserMission um WHERE um.user.id = :userId AND um.missionStatus = :status")
    Page<UserMission> findMyMissions(@Param("userId") Long userId, @Param("status") MissionStatus status, Pageable pageable);
}