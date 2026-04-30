package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "member") // 테이블명: member
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId; // bigint -> Long

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender; // varchar2(ENUM) -> Enum Class

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday; // date -> LocalDate

    @Column(name = "address", nullable = false)
    private String address; // varchar2 -> String

    @Column(name = "name", nullable = false)
    private String name; // varchar2 -> String

    @Column(name = "profileUrl", nullable = false)
    private String profileUrl; // varchar2 -> String

    @Column(name = "email", nullable = false)
    private String email; // varchar2 -> String

    @Column(name = "phone", nullable = false)
    private String phone; // varchar2 -> String

    @Column(name = "point", nullable = false)
    private Integer point; // bigint -> Long
}