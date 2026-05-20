package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.BaseEntity;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="rewardPoint",nullable = false)
    private Integer rewardPoint;

    @Column(name="content",nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'READY'")
    private MissionStatus status;

    @Column(name="deadline",nullable = false)
    private LocalDate deadline;
}
