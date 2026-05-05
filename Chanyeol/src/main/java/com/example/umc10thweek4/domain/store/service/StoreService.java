package com.example.umc10thweek4.domain.store.service;

import com.example.umc10thweek4.domain.mission.dto.MissionResDTO;
import com.example.umc10thweek4.domain.store.dto.StoreResDTO;
import com.example.umc10thweek4.domain.store.entity.Store;
import com.example.umc10thweek4.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreResDTO.StoreList getStoresByRegion(Long regionId, String category) {
        List<Store> stores = storeRepository.findByRegionIdAndCategory(regionId, category);

        List<StoreResDTO.StoreInfo> storeInfos = stores.stream()
                .map(s -> new StoreResDTO.StoreInfo(
                        s.getId(),
                        s.getStoreName(),
                        s.getAddress(),
                        s.getStoreImage(),
                        s.getCategory(),
                        s.getRegion().getRegionName()
                ))
                .toList();

        return new StoreResDTO.StoreList(storeInfos);
    }

    public StoreResDTO.StoreDetail getStoreDetail(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        return new StoreResDTO.StoreDetail(
                store.getId(),
                store.getStoreName(),
                store.getAddress(),
                store.getStoreImage(),
                store.getCategory(),
                store.getRegion().getRegionName()
        );
    }
}