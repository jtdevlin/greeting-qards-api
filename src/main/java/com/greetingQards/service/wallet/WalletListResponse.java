package com.greetingQards.service.wallet;

import lombok.Data;

import java.util.List;

@Data
public class WalletListResponse {
    public List<WalletSet> walletSets;
}
