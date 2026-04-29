package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.enums.MissionState;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "activated_mission")
public class ActivatedMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    @Builder.Default
    private MissionState state = MissionState.ONGOING;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "approver_code")
    private String approverCode;

    public void complete() {
        this.state = MissionState.COMPLETED;
        this.completedAt = LocalDateTime.now();
    }

    public void assignApproverCode(String code) {
        this.approverCode = code;
    }
}
