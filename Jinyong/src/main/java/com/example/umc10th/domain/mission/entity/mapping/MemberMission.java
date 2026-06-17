package com.example.umc10th.domain.mission.entity.mapping;

import com.example.umc10th.domain.BaseEntity;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.MemberMissionStatus;
import com.example.umc10th.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_missions")
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // mission과 연결
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY) // member와 연결
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING) // MemberMissionStatus enum
    @Column(name = "status", nullable = false)
    private MemberMissionStatus status;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}