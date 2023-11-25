import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Scanner;

/**
 * Application to encrypt and decrypt an 18-byte message using DES in CBC mode with PKCS5 padding.
 * Performs password-based key derivation with random salt and 60 iterations.
 * Password input is via keyboard.
 */
public class DES_CBC_PKCS5_PasswordEncryption {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Message:");
            String inputMessage = scanner.nextLine();

            System.out.println("Password:");
            String password = scanner.nextLine();

            // Generate random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[8];
            random.nextBytes(salt);

            // Perform password-based key derivation
            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 60, 56);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey secretKey = keyFactory.generateSecret(keySpec);

            // Initialize DES cipher in CBC mode with PKCS5 padding
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[8]);

            // Encrypt the input message
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            byte[] encryptedMessage = cipher.doFinal(inputMessage.getBytes());

            // Display encrypted message
            String encryptedBase64 = Base64.getEncoder().encodeToString(encryptedMessage);
            System.out.println("Encrypted Message: " + encryptedBase64);

            // Decrypt the encrypted message
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            byte[] decryptedMessage = cipher.doFinal(encryptedMessage);

            // Display decrypted message
            System.out.println("Decrypted Message: " + new String(decryptedMessage));

        } catch ( NoSuchAlgorithmException | InvalidKeySpecException |
                  NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException |
                  BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }
}
