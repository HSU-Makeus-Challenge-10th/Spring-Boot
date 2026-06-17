package com.example.umc10th.domain.food.repository;

import com.example.umc10th.domain.food.entity.mapping.MemberFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}