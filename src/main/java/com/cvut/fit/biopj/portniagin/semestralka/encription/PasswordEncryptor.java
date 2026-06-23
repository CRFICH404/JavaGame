package com.cvut.fit.biopj.portniagin.semestralka.encription;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordEncryptor {

    private static final byte[] SALT = "ToG-Salt-16bytes".getBytes(StandardCharsets.UTF_8);
    private static final int ITERATIONS = 65536;
    private static final int KEY_BITS = 256;

    public String encrypt(String plaintext) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(plaintext.toCharArray(), SALT, ITERATIONS, KEY_BITS);
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }
}
