package com.example.umc10thweek4.domain.review.entity;

import com.example.umc10thweek4.domain.member.entity.Member;
import com.example.umc10thweek4.domain.mission.entity.mapping.UserMission;
import com.example.umc10thweek4.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_mission_id", nullable = false)
    private UserMission userMission;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Float rating;        // 별점 (예: 4.5)

    @Column(name = "review_date", nullable = false)
    private LocalDateTime reviewDate;
}