package com.greetingQards.service.wallet;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WalletSet {
    private String id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String custodyType;
    private String name;
}