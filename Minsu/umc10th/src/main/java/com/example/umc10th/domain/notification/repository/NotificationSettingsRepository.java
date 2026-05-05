package com.example.umc10th.domain.notification.repository;

import com.example.umc10th.domain.notification.entity.NotificationSettings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationSettingsRepository extends JpaRepository<NotificationSettings, Long> {
    Optional<NotificationSettings> findByMemberId(Long memberId);
}
