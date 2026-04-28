package com.study.UMC10.domain.user.entity;

import com.study.UMC10.domain.user.enums.Gender;
import com.study.UMC10.domain.user.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user") // ERD의 테이블 이름인 'user'와 맞춥니다.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // ERD의 사용자ID
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, columnDefinition = "VARCHAR(15) DEFAULT 'UNKNOWN'")
    private Gender gender;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", nullable = false, length = 100)
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

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}