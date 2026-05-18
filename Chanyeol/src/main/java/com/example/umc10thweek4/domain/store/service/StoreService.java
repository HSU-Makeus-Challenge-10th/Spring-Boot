package com.example.umc10thweek4.domain.store.service;

import com.example.umc10thweek4.domain.mission.dto.MissionResDTO;
import com.example.umc10thweek4.domain.store.converter.StoreConverter;
import com.example.umc10thweek4.domain.store.dto.StoreResDTO;
import com.example.umc10thweek4.domain.store.entity.Store;
import com.example.umc10thweek4.domain.store.exception.StoreException;
import com.example.umc10thweek4.domain.store.exception.code.StoreErrorCode;
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

        return StoreConverter.toStoreList(stores);
    }

    public StoreResDTO.StoreDetail getStoreDetail(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        return StoreConverter.toStoreDetail(store);
    }
}