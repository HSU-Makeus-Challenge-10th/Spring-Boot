package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.store.entity.Region;
import com.example.umc10th.global.entity.BaseEntity;
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
@Table(name = "Member")
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    //EnumType.STRING DB에 문자열을 저장
    //EnumType.ORDINAL : Enum의 순서를 DB에 저장 , 거의 안씀
    private Gender gender = Gender.MALE;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "user_point")
    private Integer userPoint;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberFood> memberFoodList = new ArrayList<>();
}
