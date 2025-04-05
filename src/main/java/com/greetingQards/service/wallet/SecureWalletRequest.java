package com.greetingQards.service.wallet;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class SecureWalletRequest {
    private String entitySecretCiphertext;
    private String idempotencyKey;
}
