package com.study.UMC10.domain.user.repository;

import com.study.UMC10.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}