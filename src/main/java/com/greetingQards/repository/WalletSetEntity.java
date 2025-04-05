package com.greetingQards.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Entity(name = "WALLET_SET")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletSetEntity {
    @Id
    private String id;
    @Column(name = "nm")
    private String name;
    @OneToOne
    @JoinColumn(name = "usr_id", referencedColumnName = "id")
    private UserEntity user;
    @Column(name = "crtd_ts")
    private LocalDateTime createdTimestamp;
    @Column(name = "updt_ts")
    private LocalDateTime updatedTimestamp;
}
