package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MissionService {

    public MissionResDTO.CreateReviewRes createReview(Long missionId, MissionReqDTO.CreateReviewReq request, List<MultipartFile> reviewImages) {
        throw new UnsupportedOperationException("구현 예정");
    }
}
