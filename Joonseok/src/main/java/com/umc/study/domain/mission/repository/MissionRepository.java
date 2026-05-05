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

    @Query("""
select m
from Mission m
where m.user.location.streetAddress = :streetAddress
    and m.id not in (
        select mh.mission.id
        from MissionHistory mh
        where mh.user.id = :userId
    )
""")
    Page<Mission> findByLocationStreetAddress(
            @Param("streetAddress") String streetAddress,
            @Param("userId") Long userId,
            Pageable pageable
    );
}
