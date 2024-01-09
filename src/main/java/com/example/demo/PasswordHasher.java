package com.example.demo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHasher {
    private static final String HASH_ALGORITHM = "SHA-512";
    private static final int SALT_LENGTH = 16;

    public static String hashPassword(String password) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_LENGTH];
            random.nextBytes(salt);

            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());

            // Encode the salt and hashed password using Base64 encoding
            String encodedSalt = Base64.getEncoder().encodeToString(salt);
            String encodedHashedPassword = Base64.getEncoder().encodeToString(hashedPassword);

            // Return the combined salt and hashed password
            return encodedSalt + "$" + encodedHashedPassword;
        } catch (NoSuchAlgorithmException e) {
            // Handle exception
            return null;
        }
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        try {
            // Split the stored hash into the salt and hashed password parts
            String[] parts = hashedPassword.split("\\$");
            String encodedSalt = parts[0];
            String encodedHashedPassword = parts[1];

            // Decode the salt and hashed password using Base64 decoding
            byte[] salt = Base64.getDecoder().decode(encodedSalt);
            byte[] storedHashedPassword = Base64.getDecoder().decode(encodedHashedPassword);

            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            md.update(salt);
            byte[] hashedPasswordToVerify = md.digest(password.getBytes());

            // Compare the stored hashed password with the hashed password to verify
            return MessageDigest.isEqual(storedHashedPassword, hashedPasswordToVerify);
        } catch (NoSuchAlgorithmException e) {
            // Handle exception
            return false;
        }
    }
}
