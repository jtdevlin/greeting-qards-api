package com.greetingQards.service.wallet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CreateWalletSetRequest extends SecureWalletRequest {
    private String name;
}
