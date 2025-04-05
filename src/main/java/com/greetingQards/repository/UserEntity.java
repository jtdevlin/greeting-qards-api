package com.greetingQards.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "USR")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private String id;
    @Column(name = "nm")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "crtd_ts")
    private LocalDateTime createdTimestamp;
    @Column(name = "pwd")
    private String password;
    @Column(name = "roles")
    private List<String> roles;
    @OneToOne(mappedBy = "user")
    private WalletSetEntity walletSet;
}