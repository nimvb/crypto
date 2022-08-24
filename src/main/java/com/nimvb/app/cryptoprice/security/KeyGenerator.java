package com.nimvb.app.cryptoprice.security;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public final class KeyGenerator {
    private static final String PASSWORD = "P@ssw0rd";
    private static final String SALT = "SALT";

    public static SecretKey generate() throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory     = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec          spec        = new PBEKeySpec(PASSWORD.toCharArray(), SALT.getBytes(), 65536, 256);
        return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
    }
}
