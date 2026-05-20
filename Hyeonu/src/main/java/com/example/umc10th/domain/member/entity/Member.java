package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.BaseEntity;
import com.example.umc10th.domain.member.enums.Address;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.MemberStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="gender", nullable=false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="birth", nullable=false)
    private LocalDate birth;

    @Column(name="address", nullable=false)
    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(name="detail_address", nullable=false)
    private String detailAddress;

    @Column(name="phoneNumber", nullable=false)
    private String phoneNumber;

    @Column(name="email", nullable=false)
    private String email;

    @Column(name="point", nullable=false)
    @Builder.Default
    private int point=0;

    @Column(name="status", nullable=false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private MemberStatus status=MemberStatus.ACTIVE;

}
