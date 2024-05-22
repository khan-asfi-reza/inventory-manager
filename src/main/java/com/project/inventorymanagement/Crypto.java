package com.project.inventorymanagement;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class provides utility methods for cryptographic operations (Only md5).
 * The encrypt method encrypts a text in md5 hashing algorithm
 */
public class Crypto {

    /**
     * Encrypts a given text using MD5 hashing algorithm.
     * More on MD5 (Message Digest Algorithm): <a href="https://en.wikipedia.org/wiki/MD5">MD5</a>
     *
     * @param text the input text to be encrypted
     * @return the MD5 hash of the input text as a hexadecimal string
     * @throws RuntimeException if the MD5 hashing algorithm is not available
     */
    public static String encrypt(String text) {
        try {
            // MessageDigest instance for MD5 hashing
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Update the digest using the bytes of the input string
            md.update(text.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            // Convert each byte of the hash to a hexadecimal string,
            // where each byte is represented as two hexadecimal digits.
            // %02x makes sure each byte converts into unsigned two hexadecimal digit
            for (byte aByte : bytes) {
                sb.append(String.format("%02x", aByte));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
