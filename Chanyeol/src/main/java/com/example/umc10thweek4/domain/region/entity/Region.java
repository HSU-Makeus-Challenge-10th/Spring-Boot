package com.example.umc10thweek4.domain.region.entity;

import com.example.umc10thweek4.domain.store.entity.Store;
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
@Table(name = "region")
public class Region extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "region_name", nullable = false, length = 50)
    private String regionName;

    @Column(name = "region_image")
    private String regionImage;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Store> stores = new ArrayList<>();
}