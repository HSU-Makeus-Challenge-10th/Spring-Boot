package com.example.umc10th.domain.store.repository;

import com.example.umc10th.domain.store.entity.StoreImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreImageRepository extends JpaRepository<StoreImage, Long> {
    Optional<StoreImage> findFirstByStoreIdOrderByIdAsc(Long storeId);
}
