/**
 * SymmetricAsymmetric class demonstrates various cryptographic tasks:
 * task1: Generates a random AES key using a SecureRandom generator and
 * performs AES encryption in ECB mode with no padding. Then decrypts the ciphertext using the same key.
 * task2: Similar to task1 but uses a specified plaintext instead of a randomly generated one.
 * task3: Uses AES in CBC mode with PKCS5Padding to encrypt a given plaintext. Decrypts
 * the ciphertext back to plaintext using the same key and initialization vector (IV).
 * task4: Generates an RSA key pair, encrypts a randomly generated symmetric key using RSA,
 * and then decrypts it using the private key.
 * task5: Derives an AES key from a password using PBKDF2WithHmacSHA1 algorithm, encrypts and
 * decrypts plaintext using AES in ECB mode with no padding.
 */

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;


class SymmetricAsymmetric_Encryption {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeySpecException {
        task5();
    }

    public static void task1() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        byte[] keyBytes = new byte[16];
        // declare secure PRNG
        SecureRandom myPRNG = new SecureRandom();
        // seed the key
        myPRNG.nextBytes(keyBytes);
        // build the key from random key bytes
        SecretKeySpec myKey = new SecretKeySpec(keyBytes, "AES");
        // instantiate AES object for ECB with no padding
        Cipher myAES = Cipher.getInstance("AES/ECB/NoPadding");
        // initialize AES object to encrypt mode
        myAES.init(Cipher.ENCRYPT_MODE, myKey);
        // initialize plaintext
        byte[] plaintext = new byte[16];
        // initialize ciphertext
        byte[] ciphertext = new byte[16];
        // update cipher with the plaintext
        int cLength = myAES.update(plaintext, 0, plaintext.length, ciphertext, 0);
        // process remaining blocks of plaintext
        cLength += myAES.doFinal(ciphertext, cLength);
        // print plaintext and ciphertext
        System.out.println("plaintext: " + javax.xml.bind.DatatypeConverter.printHexBinary(plaintext));
        System.out.println("ciphertext: " + javax.xml.bind.DatatypeConverter.printHexBinary(ciphertext));
        // initialize AES for decryption
        myAES.init(Cipher.DECRYPT_MODE, myKey);
        // initialize a new array of bytes to place the decryption
        byte[] dec_plaintext = new byte[16];
        cLength = myAES.update(ciphertext, 0, ciphertext.length, dec_plaintext, 0);
        // process remaining blocks of ciphertext
        cLength += myAES.doFinal(dec_plaintext, cLength);
        // print the new plaintext (hopefully identical to the initial one)
        System.out.println("decrypted: " + javax.xml.bind.DatatypeConverter.printHexBinary(dec_plaintext));
    }

    public static void task2() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        byte[] keyBytes = new byte[16];
        // declare secure PRNG
        SecureRandom myPRNG = new SecureRandom();
        // seed the key
        myPRNG.nextBytes(keyBytes);
        // build the key from random key bytes
        SecretKeySpec myKey = new SecretKeySpec(keyBytes, "AES");
        // instantiate AES object for ECB with no padding
        Cipher myAES = Cipher.getInstance("AES/ECB/NoPadding");
        // initialize AES object to encrypt mode
        myAES.init(Cipher.ENCRYPT_MODE, myKey);
        // initialize plaintext
        byte[] plaintext = new byte[16];
        plaintext = "0123456789abcdef".getBytes(StandardCharsets.UTF_8);
        // initialize ciphertext
        byte[] ciphertext = new byte[16];
        // update cipher with the plaintext
        int cLength = myAES.update(plaintext, 0, plaintext.length, ciphertext, 0);
        // process remaining blocks of plaintext
        cLength += myAES.doFinal(ciphertext, cLength);
        // print plaintext and ciphertext
        System.out.println("plaintext: " + javax.xml.bind.DatatypeConverter.printHexBinary(plaintext));
        System.out.println("ciphertext: " + javax.xml.bind.DatatypeConverter.printHexBinary(ciphertext));
        // initialize AES for decryption
        myAES.init(Cipher.DECRYPT_MODE, myKey);
        // initialize a new array of bytes to place the decryption
        byte[] dec_plaintext = new byte[16];
        cLength = myAES.update(ciphertext, 0, ciphertext.length, dec_plaintext, 0);
        // process remaining blocks of ciphertext
        cLength += myAES.doFinal(dec_plaintext, cLength);
        // print the new plaintext (hopefully identical to the initial one)
        System.out.println("decrypted: " + javax.xml.bind.DatatypeConverter.printHexBinary(dec_plaintext));
    }

    public static void task3() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        byte[] keyBytes = new byte[16];
        // declare secure PRNG
        SecureRandom myPRNG = new SecureRandom();
        // seed the key
        myPRNG.nextBytes(keyBytes);
        // build the key from random key bytes
        SecretKeySpec myKey = new SecretKeySpec(keyBytes, "AES");
        // instantiate AES object for ECB with no padding
        Cipher myAES = Cipher.getInstance("AES/CBC/PKCS5Padding");
        // initialize AES object to encrypt mode
        myAES.init(Cipher.ENCRYPT_MODE, myKey, new IvParameterSpec(new byte[16]));
        // initialize plaintext
        byte[] plaintext = new byte[20];
        plaintext = "0123456789abcdef1234".getBytes(StandardCharsets.UTF_8);
        //initialize ciphertext
        byte[] ciphertext = new byte[32];
        // update cipher with the plaintext
        int cLength = myAES.update(plaintext, 0, plaintext.length, ciphertext, 0);
        // process remaining blocks of plaintext
        cLength += myAES.doFinal(ciphertext, cLength);
        // print plaintext and ciphertext
        System.out.println("plaintext: " + javax.xml.bind.DatatypeConverter.printHexBinary(plaintext));
        System.out.println("ciphertext: " + javax.xml.bind.DatatypeConverter.printHexBinary(ciphertext));
        // initialize AES for decryption
        myAES.init(Cipher.DECRYPT_MODE, myKey, new IvParameterSpec(new byte[16]));
        // initialize a new array of bytes to place the decryption
        byte[] dec_plaintext = new byte[32];
        cLength = myAES.update(ciphertext, 0, ciphertext.length, dec_plaintext, 0);
        // process remaining blocks of ciphertext
        cLength += myAES.doFinal(dec_plaintext, cLength);
        // print the new plaintext (hopefully identical to the initial one)
        System.out.println("decrypted: " + javax.xml.bind.DatatypeConverter.printHexBinary(dec_plaintext));
        System.out.println("decrypted plaintext string: " + new String(dec_plaintext, StandardCharsets.UTF_8));
    }
    public static void task4() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] keyBytes = new byte[16];
        // declare secure PRNG
        SecureRandom myPRNG = new SecureRandom();
        // seed the key
        myPRNG.nextBytes(keyBytes);
        // get a Cipher instance for RSA with PKCS1 padding
        Cipher myRSA = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        // get an instance for the Key Generator
        KeyPairGenerator myRSAKeyGen = KeyPairGenerator.getInstance("RSA");
        // generate an 1024-bit key
        myRSAKeyGen.initialize(1024, myPRNG);
        KeyPair myRSAKeyPair= myRSAKeyGen.generateKeyPair();
        // store the public and private key individually
        Key pbKey = myRSAKeyPair.getPublic();
        Key pvKey = myRSAKeyPair.getPrivate();
        // init cipher for encryption
        myRSA.init(Cipher.ENCRYPT_MODE, pbKey, myPRNG);
        // encrypt; as expected we encrypt a symmetric key with RSA rather than
        // a file or some longer stream which should be encrypted with AES
        byte[] ciphertext = myRSA.doFinal(keyBytes);
        System.out.println("Cipher len" + ciphertext.length);
        // init cipher for decryption
        myRSA.init(Cipher.DECRYPT_MODE, pvKey);
        // decrypt
        byte[] plaintext = myRSA.doFinal(ciphertext);
        System.out.println("Plaintext" + plaintext.length);
        System.out.println("plaintext: " + javax.xml.bind.DatatypeConverter.printHexBinary(plaintext));
        System.out.println("ciphertext: " + javax.xml.bind.DatatypeConverter.printHexBinary(ciphertext));
        System.out.println("key bytes: " + javax.xml.bind.DatatypeConverter.printHexBinary(keyBytes));
    }

    public static void task5() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        char[] password = "short_password".toCharArray();
        byte[] salt = new byte[16];
        int iteration_count = 10000; // parameter that can be set

        int key_size = 128;
        // set salt values to random

        // declare secure PRNG
        SecureRandom myPRNG = new SecureRandom();
        myPRNG.nextBytes(salt);

        // initialize key factory for HMAC-SHA1 derivation
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        // set key specification
        PBEKeySpec pbekSpec = new PBEKeySpec(password, salt, iteration_count, key_size);
        // !!!!!!!!!!!!aici
        //
        //
        //
        // generate the key
        SecretKey myAESPBKey = new SecretKeySpec(keyFactory.generateSecret(pbekSpec).getEncoded(), "AES");
        // print the key
        System.out.println("AES key: " + javax.xml.bind.DatatypeConverter.printHexBinary(myAESPBKey.getEncoded()));

        // instantiate AES object for ECB with no padding
        Cipher myAES = Cipher.getInstance("AES/ECB/NoPadding");
        // initialize AES objecy to encrypt mode
        myAES.init(Cipher.ENCRYPT_MODE, myAESPBKey);
        // initialize plaintext
        byte[] plaintext = new byte[16];
        plaintext = "0123456789abcdef".getBytes(StandardCharsets.UTF_8);
        // initialize ciphertext
        byte[] ciphertext = new byte[16];
        // update cipher with the plaintext
        int cLength = myAES.update(plaintext, 0, plaintext.length, ciphertext, 0);
        // process remaining blocks of plaintext
        cLength += myAES.doFinal(ciphertext, cLength);
        // print plaintext and ciphertext
        System.out.println("plaintext: " + javax.xml.bind.DatatypeConverter.printHexBinary(plaintext));
        System.out.println("ciphertext: " + javax.xml.bind.DatatypeConverter.printHexBinary(ciphertext));
        // initialize AES for decryption
        myAES.init(Cipher.DECRYPT_MODE, myAESPBKey);
        // initialize a new array of bytes to place the decryption
        byte[] dec_plaintext = new byte[16];
        cLength = myAES.update(ciphertext, 0, ciphertext.length, dec_plaintext, 0);
        // process remaining blocks of ciphertext
        cLength += myAES.doFinal(dec_plaintext, cLength);
        // print the new plaintext (hopefully identical to the initial one)
        System.out.println("decrypted: " + javax.xml.bind.DatatypeConverter.printHexBinary(dec_plaintext));
        System.out.println("decrypted plaintext string: " + new String(dec_plaintext, StandardCharsets.UTF_8));
    }
}