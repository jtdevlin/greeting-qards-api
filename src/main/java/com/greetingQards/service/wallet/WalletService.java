package com.greetingQards.service.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WalletService {

    private final RestTemplate walletApiClient;

    @Autowired
    public WalletService(@Qualifier("walletApiClient") RestTemplate walletApiClient) {
        this.walletApiClient = walletApiClient;
    }

    public WalletListResponse getWalletSets() {
        ResponseEntity<WalletApiResponse<WalletListResponse>> response = walletApiClient.exchange("/walletSets", HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        return response.getBody().getData();
    }
}
