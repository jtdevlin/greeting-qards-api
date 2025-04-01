package com.greetingQards.controller;

import com.greetingQards.service.wallet.WalletApiResponse;
import com.greetingQards.service.wallet.WalletListResponse;
import com.greetingQards.service.wallet.WalletService;
import com.greetingQards.service.wallet.WalletSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/wallets")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public ResponseEntity<WalletListResponse> getWallets() {
        WalletListResponse walletSets = walletService.getWalletSets();
        return ResponseEntity.ok(walletSets);
    }
}
