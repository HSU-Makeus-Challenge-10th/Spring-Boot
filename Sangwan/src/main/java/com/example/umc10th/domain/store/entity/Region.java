package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.member.entity.mapping.RegionProgress;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "region")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Region extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    private List<Store> stores = new ArrayList<>();

    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    private List<RegionProgress> regionProgresses = new ArrayList<>();
}
