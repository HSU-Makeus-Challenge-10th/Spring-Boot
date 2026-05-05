package com.example.umc10thweek4.domain.member.entity;

import com.example.umc10thweek4.domain.member.entity.Member;
import com.example.umc10thweek4.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notice")
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String noticeTitle;

    @Column(name = "notice_detail", nullable = false, columnDefinition = "TEXT")
    private String noticeDetail;

    @Column(name = "notice_type", nullable = false)
    private String noticeType;   // REVIEW_REQUEST, NEW_MISSION 등

    @Column(nullable = false)
    private Boolean isRead = false;

    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;
}