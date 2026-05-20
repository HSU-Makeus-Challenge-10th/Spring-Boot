package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findAllByStore_Id(Long storeId, Pageable pageable);

    // MissionRepository.java
    @Query("SELECT m FROM Mission m JOIN m.memberMissionList mm WHERE mm.member.id = :memberId")
    Page<Mission> findAllByMember_Id(@Param("memberId") Long memberId, Pageable pageable);
}
