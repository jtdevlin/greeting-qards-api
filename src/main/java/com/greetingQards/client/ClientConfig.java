package com.greetingQards.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Collections;

@Configuration
public class ClientConfig {

    @Value("${wallet.api.app-id}")
    private String appId;

    @Value("${wallet.api.key}")
    private String key;

    @Bean("walletApiClient")
    public RestTemplate walletApiClient() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("https://api.circle.com/v1/w3s"));
        restTemplate.setInterceptors(Collections.singletonList(walletApiInterceptor()));
        return restTemplate;
    }

    public ClientHttpRequestInterceptor walletApiInterceptor() {
        return (request, body, execution) -> {
            request.getHeaders().setBearerAuth(key);
            return execution.execute(request, body);
        };
    }
}
