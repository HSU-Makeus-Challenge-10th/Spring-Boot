package com.example.umc10thweek4.domain.mission.entity.mapping;

import com.example.umc10thweek4.domain.member.entity.Member;
import com.example.umc10thweek4.domain.mission.entity.Mission;
import com.example.umc10thweek4.domain.mission.enums.MissionStatus;
import com.example.umc10thweek4.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_mission")
public class UserMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionStatus status = MissionStatus.IN_PROGRESS;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    public void completeMission(Integer recognizeAmount) {
        this.status = MissionStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
    }
}