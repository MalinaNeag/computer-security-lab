import java.math.BigInteger;

/**
 * Using the BigInteger class in Java to decrypt an input message using the Chinese Remainder Theorem (CRT)
 * Given RSA decryptions modulo p = 104743 and q = 7993 are c_p = 7460 and c_q = 3467.
 */
public class CRTRSA_Decryption {
    public static void main(String[] args) {
        // Define the RSA modulus and encrypted values
        BigInteger p = new BigInteger("104743");
        BigInteger q = new BigInteger("7993");
        BigInteger c_p = new BigInteger("7460");
        BigInteger c_q = new BigInteger("3467");

        // Calculate modular multiplicative inverses for CRT
        BigInteger qInverse = q.modInverse(p);
        BigInteger pInverse = p.modInverse(q);

        // Calculate the decrypted message using CRT
        BigInteger part1 = c_p.multiply(q).multiply(qInverse);
        BigInteger part2 = c_q.multiply(p).multiply(pInverse);

        BigInteger modulus = p.multiply(q);
        BigInteger M = part1.add(part2).mod(modulus);

        // Display the decrypted message
        System.out.println("The decrypted message is: " + M);
    }
}
