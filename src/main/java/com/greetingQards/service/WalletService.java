package com.greetingQards.service;

import com.greetingQards.config.WalletApiConfiguration;
import com.greetingQards.repository.UserEntity;
import com.greetingQards.repository.UserRepository;
import com.greetingQards.repository.WalletSetEntity;
import com.greetingQards.repository.WalletSetRepository;
import com.greetingQards.service.wallet.*;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
public class WalletService {

    private final RestTemplate walletApiClient;
    private final WalletSetRepository walletSetRepository;
    private final UserRepository userRepository;
    private final WalletApiConfiguration walletApiConfiguration;

    @Autowired
    public WalletService(@Qualifier("walletApiClient") RestTemplate walletApiClient,
                         WalletSetRepository walletSetRepository,
                         UserRepository userRepository, WalletApiConfiguration walletApiConfiguration) {
        this.walletApiClient = walletApiClient;
        this.walletSetRepository = walletSetRepository;
        this.userRepository = userRepository;
        this.walletApiConfiguration = walletApiConfiguration;
    }

    public WalletListResponse getWalletSets() {
        ResponseEntity<WalletApiResponse<WalletListResponse>> response = walletApiClient.exchange("/walletSets", HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        return response.getBody().getData();
    }

    public WalletSet createWalletSetForUser(String userId) {

        Optional<UserEntity> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new NotFoundException("User does not exists for id: " + userId);
        }

        CreateWalletSetRequest createWalletSetRequest = CreateWalletSetRequest.builder()
                .name("QaRds user - " + userId)
                .entitySecretCiphertext(walletApiConfiguration.getCipherText())
                .idempotencyKey(UUID.randomUUID().toString())
                .build();
        ResponseEntity<WalletApiResponse<WalletSetResponse>> response = walletApiClient.exchange("/developer/walletSets", HttpMethod.POST, new HttpEntity<>(createWalletSetRequest), new ParameterizedTypeReference<>() {});
        if(!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to create wallet set");
        }

        WalletSetResponse walletSetResponse = response.getBody().getData();
        WalletSet walletSet = walletSetResponse.getWalletSet();
        WalletSetEntity walletSetEntity = WalletSetEntity.builder()
                .createdTimestamp(walletSet.getCreateDate())
                .name(walletSet.getName())
                .id(walletSet.getId())
                .user(user.get())
                .updatedTimestamp(walletSet.getUpdateDate())
                .build();

        walletSetEntity = walletSetRepository.save(walletSetEntity);

        walletSet.setId(walletSetEntity.getId());

        return walletSet;
    }
}
