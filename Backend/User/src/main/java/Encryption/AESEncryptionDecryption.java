package myProject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Java String Encryption Decryption
 * @author Bruce Bitwayiki
 */
public class AESEncryptionDecryption {
    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static final String ALGORITHM = "AES";
    public final String passwordKey = "droppTop";

    /**
     *Given a string myKey, checks and prepares the string to be used as a key to encrypt and decrypt
     * @param myKey
     */
    public void prepareSecreteKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Encrypts and return a password given the unencrypted password and a key
     * @param strToEncrypt
     * @param secret
     * @return the encrypted string
     */
    public String encrypt(String strToEncrypt, String secret) {
        try {
            prepareSecreteKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    /**
     * Decrypts and return a password given the encrypted password and the key
     * @param strToDecrypt
     * @param secret
     * @return the decrypted password
     */
    public String decrypt(String strToDecrypt, String secret) {
        try {
            prepareSecreteKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}
