package com.study.UMC10.domain.store.repository;

import com.study.UMC10.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}