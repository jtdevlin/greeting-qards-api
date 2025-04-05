package com.greetingQards.exception;

public class CipherTextGenerationException extends RuntimeException {
    public CipherTextGenerationException(String message) {
        super(message);
    }

    public CipherTextGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
