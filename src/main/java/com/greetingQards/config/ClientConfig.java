package com.greetingQards.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Collections;

@Configuration
public class ClientConfig {

    private final WalletApiConfiguration walletApiConfiguration;

    @Autowired
    public ClientConfig(WalletApiConfiguration walletApiConfiguration) {
        this.walletApiConfiguration = walletApiConfiguration;
    }

    @Bean("walletApiClient")
    public RestTemplate walletApiClient() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(walletApiConfiguration.getBaseUrl()));
        restTemplate.setInterceptors(Collections.singletonList(walletApiInterceptor()));
        return restTemplate;
    }

    public ClientHttpRequestInterceptor walletApiInterceptor() {
        return (request, body, execution) -> {
            request.getHeaders().setBearerAuth(walletApiConfiguration.getKey());
            return execution.execute(request, body);
        };
    }
}
