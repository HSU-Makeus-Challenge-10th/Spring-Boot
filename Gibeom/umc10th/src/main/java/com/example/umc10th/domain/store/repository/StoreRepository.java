package com.example.umc10th.domain.store.repository;

import com.example.umc10th.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store,Long> {
    List<Store> findByRegion_Id(Long regionId);
}
