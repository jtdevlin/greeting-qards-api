package com.greetingQards.service.wallet;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class WalletSet {
    private String id;
    private ZonedDateTime createDate;
    private ZonedDateTime updateDate;
    private String custodyType;
    private String userId;
}