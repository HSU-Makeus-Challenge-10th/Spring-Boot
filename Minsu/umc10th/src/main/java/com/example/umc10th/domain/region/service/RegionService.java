package com.example.umc10th.domain.region.service;

import com.example.umc10th.domain.region.entity.Town;
import com.example.umc10th.domain.region.repository.TownRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionService {

    private final TownRepository townRepository;

    public List<Town> getAllTowns() {
        return townRepository.findAll();
    }
}
