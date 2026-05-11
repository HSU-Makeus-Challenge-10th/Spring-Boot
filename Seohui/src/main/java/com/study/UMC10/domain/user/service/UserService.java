package com.study.UMC10.domain.user.service;

import com.study.UMC10.domain.mission.entity.Mission;
import com.study.UMC10.domain.mission.repository.MissionRepository;
import com.study.UMC10.domain.user.code.UserErrorCode;
import com.study.UMC10.domain.user.code.UserException;
import com.study.UMC10.domain.user.converter.UserConverter;
import com.study.UMC10.domain.user.dto.request.UserRequestDto;
import com.study.UMC10.domain.user.dto.response.UserResponseDto;
import com.study.UMC10.domain.user.entity.User;
import com.study.UMC10.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MissionRepository missionRepository;

    // 마이페이지
    @Transactional(readOnly = true)
    public UserResponseDto.GetInfo getInfo(UserRequestDto.GetInfo dto) {
        Long userId = dto.id();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.MEMBER_NOT_FOUND));

        return UserResponseDto.GetInfo.builder()
                .name(user.getName())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .phoneVerified(user.getIsPhone())
                .phoneNum(user.getPhoneNum())
                .totalPoint(user.getTotalPoint())
                .build();
    }

    public UserResponseDto.SignUpResultDto signUp(UserRequestDto.SignUpDto requestDto) {
        return null;
    }

    // 홈 화면 (지역별 미션 조회 + 페이징)
    @Transactional(readOnly = true)
    public UserResponseDto.HomeResultDto getHome(String region, Integer page) {

        Long dummyUserId = 1L;
        User user = userRepository.findById(dummyUserId)
                .orElseThrow(() -> new UserException(UserErrorCode.MEMBER_NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 10);

        Page<Mission> missionPage = missionRepository.findMissionsByRegion(region, pageRequest);

        List<UserResponseDto.HomeMissionDto> missionDtoList = missionPage.stream()
                .map(mission -> UserResponseDto.HomeMissionDto.builder()
                        .missionId(mission.getId())
                        .storeName(mission.getStore().getStoreName())
                        .storeCategory(mission.getStore().getFoodCategory().getCategoryName())
                        .reward(mission.getRewardPoint())
                        .deadline(mission.getDeadline().getDayOfMonth())
                        .status("도전 가능")
                        .build())
                .collect(Collectors.toList());

        return UserResponseDto.HomeResultDto.builder()
                .local(region)
                .point(user.getTotalPoint())
                .missionSuccess(user.getFinMission())
                .missions(missionDtoList)
                .build();
    }
}