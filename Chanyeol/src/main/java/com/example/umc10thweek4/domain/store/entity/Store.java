package com.example.umc10thweek4.domain.store.entity;

import com.example.umc10thweek4.domain.region.entity.Region;
import com.example.umc10thweek4.domain.mission.entity.Mission;
import com.example.umc10thweek4.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(nullable = false)
    private String address;

    @Column(name = "store_image")
    private String storeImage;

    @Column(nullable = false)
    private String category;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Mission> missions = new ArrayList<>();
}