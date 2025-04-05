package com.greetingQards.controller;

import com.greetingQards.domain.User;
import com.greetingQards.service.wallet.WalletListResponse;
import com.greetingQards.service.WalletService;
import com.greetingQards.service.wallet.WalletSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("walletSets")
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

    @PostMapping
    public ResponseEntity<WalletSet> createWalletSetForUser() {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authenticationToken.getPrincipal();
        WalletSet walletSet = walletService.createWalletSetForUser(user.getId());
        return ResponseEntity.ok(walletSet);
    }
}
