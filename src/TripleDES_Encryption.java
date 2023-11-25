/**
 * Implement a Java application that enables the user to encrypt and decrypt
 * a 26-byte message using 3DES (DeSede) in CBC mode with PKCS5 padding.
 * The application must perform a password-based key derivation that accepts
 * a random salt and 50 iterations as parameters. The password must be read from a text file.
 */

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class TripleDES_Encryption {
    public static void main(String[] args) {

        String inputMessage = "SecretMessage26Bytes";

        try {

            String password = new String(Files.readAllBytes(Paths.get("password.txt")));

            // Generate a random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[8];
            random.nextBytes(salt);

            // Create PBE key based on the password
            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 50, 192);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            SecretKey secretKey = keyFactory.generateSecret(keySpec);
            SecretKey desedeKey = new SecretKeySpec(secretKey.getEncoded(), "DESede");

            // Encryption
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[8]);
            cipher.init(Cipher.ENCRYPT_MODE, desedeKey, ivParameterSpec);
            byte[] encryptedMessage = cipher.doFinal(inputMessage.getBytes());

            System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(encryptedMessage));

            // Decryption
            cipher.init(Cipher.DECRYPT_MODE, desedeKey, ivParameterSpec);
            byte[] decryptedMessage = cipher.doFinal(encryptedMessage);

            System.out.println("Decrypted Message: " + new String(decryptedMessage));

        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException |
                 NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException |
                 BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }
}

