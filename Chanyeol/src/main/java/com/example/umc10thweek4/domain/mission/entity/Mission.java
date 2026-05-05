package com.example.umc10thweek4.domain.mission.entity;

import com.example.umc10thweek4.domain.store.entity.Store;
import com.example.umc10thweek4.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "mission_title", nullable = false)
    private String missionTitle;

    @Column(name = "mission_detail", nullable = false, columnDefinition = "TEXT")
    private String missionDetail;

    @Column(name = "mission_deadline", nullable = false)
    private LocalDateTime missionDeadline;

    @Column(name = "mission_reward", nullable = false)
    private Long missionReward;

    @Column(name = "target_amount")
    private Integer targetAmount;
}