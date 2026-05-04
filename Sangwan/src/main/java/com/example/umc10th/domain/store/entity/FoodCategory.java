package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.member.entity.mapping.MemberFoodCategory;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "food_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class FoodCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @OneToMany(mappedBy = "foodCategory", fetch = FetchType.LAZY)
    private List<MemberFoodCategory> memberFoodCategories = new ArrayList<>();

    @OneToMany(mappedBy = "foodCategory", fetch = FetchType.LAZY)
    private List<Store> stores = new ArrayList<>();
}
