package com.example.umc10th.domain.notification.entity;

import com.example.umc10th.domain.notification.enums.NotificationTargetType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notification_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class NotificationCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private NotificationTargetType targetType;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<NotificationEvent> notificationEvents = new ArrayList<>();

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<NotificationSetting> notificationSettings = new ArrayList<>();
}
