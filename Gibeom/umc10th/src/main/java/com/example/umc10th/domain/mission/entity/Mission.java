package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.store.entity.mapping.StoreMission;
import com.example.umc10th.global.entity.BaseEntity;
import com.example.umc10th.domain.member.entity.mapping.MemberMission;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<StoreMission> storeMissionList = new ArrayList<>();

    @Column(name = "reward_point", nullable = false)
    private Integer rewardPoint;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<MemberMission> memberMissionList = new ArrayList<>();

}
