package com.study.UMC10.domain.user.repository;

import com.study.UMC10.domain.user.entity.UserLogin;
import com.study.UMC10.domain.user.enums.LoginType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
    Optional<UserLogin> findByLoginTypeAndSocialId(LoginType loginType, String socialId);
}