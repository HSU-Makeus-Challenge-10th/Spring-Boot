package com.example.umc10th.domain.notification.entity;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "notification_settings")
public class NotificationSettings extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "event_enabled", nullable = false)
    @Builder.Default
    private Boolean eventEnabled = true;

    @Column(name = "review_reply_enabled", nullable = false)
    @Builder.Default
    private Boolean reviewReplyEnabled = true;

    @Column(name = "inquiry_reply_enabled", nullable = false)
    @Builder.Default
    private Boolean inquiryReplyEnabled = true;

    public void update(Boolean eventEnabled, Boolean reviewReplyEnabled, Boolean inquiryReplyEnabled) {
        if (eventEnabled != null) {
            this.eventEnabled = eventEnabled;
        }
        if (reviewReplyEnabled != null) {
            this.reviewReplyEnabled = reviewReplyEnabled;
        }
        if (inquiryReplyEnabled != null) {
            this.inquiryReplyEnabled = inquiryReplyEnabled;
        }
    }
}
