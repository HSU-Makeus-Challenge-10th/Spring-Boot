package com.umc.study.domain.user.entity;

import com.umc.study.domain.user.enums.Role;
import com.umc.study.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean isMale;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNum;

    @Column(nullable = false)
    private Integer pointDeposit;

    public boolean isOwner() {
        return this.role == Role.OWNER;
    }
}
