package com.umc.study.domain.user.service;

import com.umc.study.domain.mission.entity.Mission;
import com.umc.study.domain.mission.entity.Restaurant;
import com.umc.study.domain.mission.exception.PageOverflowException;
import com.umc.study.domain.mission.repository.MissionRepository;
import com.umc.study.domain.mission.repository.RestaurantRepository;
import com.umc.study.domain.user.entity.User;
import com.umc.study.domain.user.exception.UserNotFoundException;
import com.umc.study.domain.user.repository.UserRepository;
import com.umc.study.domain.user.web.dto.GetHomeRes;
import com.umc.study.domain.user.web.dto.GetMyPageRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final RestaurantRepository restaurantRepository;

    public GetMyPageRes getMyPage(Long userId) {

        User found = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return new GetMyPageRes(
                found.getName(),
                found.getEmail(),
                found.getPhoneNum() == null ? "" : found.getPhoneNum(),
                found.getPointDeposit()
        );
    }

    public GetHomeRes getHomepage(Long userId, int page, int size) {

        // page query setting
        Pageable pageable = PageRequest.of(page - 1, size);

        // user find
        User found = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        // count total completed mission
        long completedMissionCount = userRepository.countCompletedMissions(userId);

        // find all unaccepted mission list by page query
        Page<Mission> missions = missionRepository.findByLocationStreetAddress(
                found.getLocation().getStreetAddress(),
                userId,
                pageable
        );

        // valid page value
        if (page > missions.getTotalPages())
            throw new PageOverflowException();

        boolean hasNext = page < missions.getTotalPages();

        // wrapping accept mission list
        List<GetHomeRes.NotAcceptedMission> notAcceptedMissions = missions.stream()
                .map(m -> {
                    if (!m.getUser().isOwner()) {
                        throw new IllegalArgumentException("해당 ID는 가게 주인이 아닙니다.");
                    }
                    Restaurant restaurant = restaurantRepository.findByUserId(m.getUser().getId())
                            .orElseThrow(UserNotFoundException::new);
                    return new GetHomeRes.NotAcceptedMission(
                            restaurant.getId(),
                            restaurant.getName(),
                            restaurant.getType().getKorean(),
                            m.getMinPrice(),
                            m.getPoint()
                    );
                })
                .toList();

        return new GetHomeRes(
                page,
                hasNext,
                found.getLocation().getStreetAddress(),
                found.getPointDeposit(),
                (int) completedMissionCount,
                notAcceptedMissions

        );
    }

}
