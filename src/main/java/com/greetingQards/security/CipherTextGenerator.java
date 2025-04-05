package com.greetingQards.security;

import com.greetingQards.exception.CipherTextGenerationException;
import io.jsonwebtoken.io.Decoders;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HexFormat;

public class CipherTextGenerator {

    public static String generateCipherText(String secret, String publicKeyString) {
        try {
            byte[] entitySecret = HexFormat.of().parseHex(secret);
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", ""));
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            OAEPParameterSpec oaepParameterSpec = new OAEPParameterSpec("SHA-256", "MGF1",
                    MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey, oaepParameterSpec);
            byte[] encryptData = cipher.doFinal(entitySecret);
            return Base64.getEncoder().encodeToString(encryptData);

        } catch (Throwable throwable) {
            throw new CipherTextGenerationException("Error generating cipher text", throwable);
        }
    }
}
