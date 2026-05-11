package com.example.umc10thweek4.domain.member.repository;

import com.example.umc10thweek4.domain.member.entity.mapping.MemberFoodPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFoodPreferenceRepository extends JpaRepository<MemberFoodPreference, Long> {
}