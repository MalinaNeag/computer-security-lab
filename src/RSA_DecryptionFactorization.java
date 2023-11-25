import java.math.BigInteger;

/**
 * Using the BigInteger class in Java to solve the following problem:
 * Find plaintext message 'm', known to be an integer between 8 and 16 bits,
 * and its RSA encryption (without padding) used the following parameters:
 * n = 837210799,
 * e = 7,
 * c = 453122177
 */
public class RSA_DecryptionFactorization {

    public static void main(String[] args) {

        BigInteger n = new BigInteger("837210799");
        BigInteger e = new BigInteger("7");
        BigInteger c = new BigInteger("453122177");

        // Factorize n to retrieve p and q
        BigInteger[] factors = factorizeN(n);
        if (factors != null) {
            BigInteger p = factors[0];
            BigInteger q = factors[1];

            // Compute Euler's totient function
            BigInteger phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

            // Calculate the modular multiplicative inverse of 'e' mod phiN to get 'd'
            BigInteger d = e.modInverse(phiN);

            // Decrypt the ciphertext 'c' using 'd' and 'n' to get the original message 'm'
            BigInteger m = c.modPow(d, n);

            System.out.println("Original message: " + m);
        } else {
            System.out.println("Unable to factorize n. No prime factors found.");
        }
    }

    // Method to factorize 'n' to retrieve its prime factors 'p' and 'q'
    private static BigInteger[] factorizeN(BigInteger n) {
        BigInteger sqrtN = sqrt(n);
        BigInteger i = new BigInteger("2");
        while (i.compareTo(sqrtN) <= 0) {
            if (n.mod(i).equals(BigInteger.ZERO) && i.isProbablePrime(100)) {
                BigInteger q = n.divide(i);
                return new BigInteger[]{i, q};
            }
            i = i.add(BigInteger.ONE);
        }
        return null;
    }

    // Method to calculate square root for BigInteger
    private static BigInteger sqrt(BigInteger x) {
        BigInteger sqrt = x;
        while (sqrt.multiply(sqrt).compareTo(x) > 0) {
            sqrt = sqrt.add(x.divide(sqrt)).shiftRight(1);
        }
        return sqrt;
    }
}
