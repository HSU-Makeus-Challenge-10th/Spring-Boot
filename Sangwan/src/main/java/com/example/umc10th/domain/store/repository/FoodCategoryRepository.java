package com.example.umc10th.domain.store.repository;

import com.example.umc10th.domain.store.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
