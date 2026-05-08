package com.umc.study.domain.mission.service;

import com.umc.study.domain.mission.entity.Mission;
import com.umc.study.domain.mission.entity.Restaurant;
import com.umc.study.domain.mission.exception.PageOverflowException;
import com.umc.study.domain.mission.repository.MissionRepository;
import com.umc.study.domain.mission.repository.RestaurantRepository;
import com.umc.study.domain.mission.web.dto.GetAcceptMissionRes;
import com.umc.study.domain.mission.web.dto.GetComplMissionRes;
import com.umc.study.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final RestaurantRepository restaurantRepository;

    // get Complete Mission History
    public GetComplMissionRes getComplMission(Long userId, int page) {

        Pageable pageable = getPageable(page);

        // 조회
        Page<Mission> found = missionRepository.findMissionByUserIdAndIsCompleted(userId, true, pageable);

        // 유효한 페이지 값인지 검증
        validPage(page, found);

        // DTO 객체로 래핑
        List<GetComplMissionRes.CompletedMission> missionList = found.stream()
                .map(m -> {

                    if (!m.getUser().isOwner()) {
                        throw new IllegalArgumentException("해당 ID는 가게 주인이 아닙니다.");
                    }
                    Restaurant restaurant = restaurantRepository.findByUserId(m.getUser().getId())
                            .orElseThrow(UserNotFoundException::new);
                    return new GetComplMissionRes.CompletedMission(
                            m.getId(),
                            m.getPoint(),
                            restaurant.getId(),
                            restaurant.getName(),
                            m.getMinPrice()
                    );
                })
                .toList();

        // 반환
        return new GetComplMissionRes(page, isHasNext(page, found), missionList);
    }

    private boolean isHasNext(int page, Page<Mission> found) {
        boolean hasNext = page < found.getTotalPages();
        return hasNext;
    }

    public GetAcceptMissionRes getAcceptMission(Long userId, int page) {

        // 1 페이지당 5개
        Pageable pageable = getPageable(page);

        // 조회
        Page<Mission> found = missionRepository.findMissionByUserIdAndIsCompleted(userId, false, pageable);

        // 유효한 페이지 값인지 검증
        validPage(page, found);

        // DTO 객체로 래핑
        List<GetAcceptMissionRes.InProgressMission> missionList = found.stream()
                .map(m -> {

                    if (!m.getUser().isOwner()) {
                        throw new IllegalArgumentException("해당 ID는 가게 주인이 아닙니다.");
                    }
                    Restaurant restaurant = restaurantRepository.findByUserId(m.getUser().getId())
                            .orElseThrow(UserNotFoundException::new);
                    return new GetAcceptMissionRes.InProgressMission(
                            m.getId(),
                            m.getPoint(),
                            restaurant.getId(),
                            restaurant.getName(),
                            m.getMinPrice()
                    );
                })
                .toList();

        // 반환
        return new GetAcceptMissionRes(page, isHasNext(page, found), missionList);
    }

    private void validPage(int page, Page<Mission> found) {
        if (page > found.getTotalPages())
            throw new PageOverflowException();
    }

    private @NonNull Pageable getPageable(int page) {
        // 1 페이지당 5개
        Pageable pageable = PageRequest.of(page - 1, 5);
        return pageable;
    }
}
