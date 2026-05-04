package com.example.umc10th.domain.notification.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notification_event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class NotificationEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cetegory_id", nullable = false)
    private NotificationCategory category;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Long targetId;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<MemberNotification> memberNotifications = new ArrayList<>();
}
