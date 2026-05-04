package com.example.umc10th.domain.point.entity;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.RegionProgress;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.point.enums.PointSourceType;
import com.example.umc10th.domain.point.enums.PointType;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "point_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class PointHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PointType type;

    private String description;

    @Enumerated(EnumType.STRING)
    private PointSourceType sourceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_withdrawal_id")
    private PointWithdrawal pointWithdrawal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_progress_id")
    private RegionProgress regionProgress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_mission_id")
    private MemberMission memberMission;
}
