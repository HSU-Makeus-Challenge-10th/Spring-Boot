package com.example.umc10th.domain.store.service;

import com.example.umc10th.domain.store.converter.StoreConverter;
import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public List<StoreResDTO.StoreDTO> getStoreList(Long regionId) {
        List<Store> stores = storeRepository.findByRegion_Id(regionId);
        return StoreConverter.toStoreDtoList(stores);
    }
}
