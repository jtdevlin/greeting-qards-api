package com.greetingQards.service.wallet;

import lombok.Data;

@Data
public class WalletApiResponse<T> {

    private T data;
}
