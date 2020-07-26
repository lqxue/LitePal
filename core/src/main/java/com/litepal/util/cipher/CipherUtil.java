package com.litepal.util.cipher;import android.text.TextUtils;import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;/**
 * Utility to manage encryption and decryption for different algorithms.
 *
 * @author Tony Green
 * @since 1.6
 */
public class CipherUtil {private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};public static String aesKey = "LitePalKey";/**
     * Encrypt the plain text with AES algorithm.
     * @param plainText
     *          The plain text.
     * @return The Encrypt content.
     */
    public static String aesEncrypt(String plainText) {
        if (TextUtils.isEmpty(plainText)) {
            return plainText;
        }
        try {
            return AESCrypt.encrypt(aesKey, plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }/**
     * Decrypt the encrypted text with AES algorithm.
     * @param encryptedText
     *          The encrypted text.
     * @return The plain content.
     */
    public static String aesDecrypt(String encryptedText) {
        if (TextUtils.isEmpty(encryptedText)) {
            return encryptedText;
        }
        try {
            return AESCrypt.decrypt(aesKey, encryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }/**
     * Encrypt the plain text with MD5 algorithm.
     * @param plainText
     *          The plain text.
     * @return The Encrypt content.
     */
    public static String md5Encrypt(String plainText) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(plainText.getBytes(Charset.defaultCharset()));
            return new String(toHex(digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }private static char[] toHex(byte[] data) {
        char[] toDigits = DIGITS_UPPER;
        int l = data.length;
        char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }}