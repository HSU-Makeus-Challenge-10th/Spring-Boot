package com.study.UMC10.domain.user.entity;

import com.study.UMC10.domain.mission.entity.UserMission;
import com.study.UMC10.domain.review.entity.Review;
import com.study.UMC10.domain.user.enums.Gender;
import com.study.UMC10.domain.user.enums.UserStatus;
import com.study.UMC10.global.apiPayload.code.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    @Builder.Default
    private String name = " ";

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "email", nullable = false, length = 40)
    private String email;

    @Column(name = "nickname", length = 20)
    private String nickname;

    @Column(name = "phone_num", length = 11)
    private String phoneNum;

    @Column(name = "is_phone", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isPhone;

    @Column(name = "total_point", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer totalPoint;

    @Column(name = "fin_mission", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer finMission;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private UserStatus status;

    //
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPreferFood> userPreferFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserAgree> userAgreeList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserMission> userMissionList = new ArrayList<>();
}