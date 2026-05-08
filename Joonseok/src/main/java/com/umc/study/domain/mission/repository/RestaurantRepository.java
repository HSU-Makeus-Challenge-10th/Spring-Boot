package com.umc.study.domain.mission.repository;

import com.umc.study.domain.mission.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByUserId(Long userId);
}
