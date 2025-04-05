package com.greetingQards.config;

import com.greetingQards.security.CipherTextGenerator;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "wallet.api")
public class WalletApiConfiguration {
    private String host;
    private String basePath;
    private String key;
    private String appId;
    private String publicKey;
    private String secret;

    public String getBaseUrl() {
        return host + basePath;
    }

    public String getCipherText() {
        return CipherTextGenerator.generateCipherText(secret, publicKey);
    }
}
