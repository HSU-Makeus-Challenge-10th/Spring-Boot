package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.mapping.MemberFoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFoodCategoryRepository extends JpaRepository<MemberFoodCategory, Long> {
}
