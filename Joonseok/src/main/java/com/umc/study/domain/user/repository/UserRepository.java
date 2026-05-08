package com.umc.study.domain.user.repository;

import com.umc.study.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    @Query("""
select count(mh)
from User u
    join u.missionLogs mh
where u.id = :userId
    and mh.isCompleted = true
""")
    long countCompletedMissions(@Param("userId") Long userId);

}
