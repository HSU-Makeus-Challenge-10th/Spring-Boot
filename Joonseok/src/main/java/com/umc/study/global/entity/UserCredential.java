package com.umc.study.global.entity;

import com.umc.study.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCredential {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credential_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Lob
    @Column(nullable = false)
    private byte[] publicKey;

    private Long signatureCount;

    private boolean uvInitialized;

    @Column(nullable = false)
    private boolean backupEligible;

    private String authenticatorTransports;

    private String publicKeyCredentialType;

    @Column(nullable = false)
    private boolean backupState;

    private byte[] attestationObject;

    private byte[] attestationClientDataJson;

    @Column(updatable =false)
    private LocalDateTime created;

    private LocalDateTime lastUsed;

    @Column(nullable = false)
    private String label;
}
