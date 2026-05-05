package com.umc.study.domain.mission.repository;

import com.umc.study.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    //
    @Query("""
select mh.mission
from MissionHistory mh
where mh.user.id = :userId
    and mh.isCompleted = :isCompleted
""")
    Page<Mission> findMissionByUserIdAndIsCompleted(
            @Param("userId") Long userId,
            @Param("isCompleted") Boolean isCompleted,
            Pageable pageable
    );
}
