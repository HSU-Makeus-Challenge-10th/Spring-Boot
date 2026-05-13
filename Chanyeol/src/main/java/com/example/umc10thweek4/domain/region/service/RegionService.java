package com.example.umc10thweek4.domain.region.service;

import com.example.umc10thweek4.domain.region.converter.RegionConverter;
import com.example.umc10thweek4.domain.region.dto.RegionResDTO;
import com.example.umc10thweek4.domain.region.entity.Region;
import com.example.umc10thweek4.domain.region.exception.RegionException;
import com.example.umc10thweek4.domain.region.exception.code.RegionErrorCode;
import com.example.umc10thweek4.domain.region.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionResDTO.RegionList getAllRegions() {
        List<Region> regions = regionRepository.findAllByDeletedAtIsNullOrderByRegionNameAsc();

        return RegionConverter.toRegionList(regions);
    }

    public RegionResDTO.RegionInfo getRegion(Long regionId) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new RegionException(RegionErrorCode.REGION_NOT_FOUND));

        return RegionConverter.toRegionInfo(region);
    }
}