import java.math.BigInteger;

/**
 * Given the RSA key pair:
 * n: Modulus
 * e: Public exponent
 * d: Private exponent
 * The task is to factorize the modulus n into its prime factors p and q.
 */

public class RSA_Factorization_NED {

    public static void main(String[] args) {

        BigInteger n = new BigInteger("5076313634899413540120536350051034312987619378778911504647420938544746517711031490115528420427319479274407389058253897498557110913160302801741874277608327");
        BigInteger e = new BigInteger("3");
        BigInteger d = new BigInteger("3384209089932942360080357566700689541991746252519274336431613959029831011807259226655786125050887727921274719751986104162037800807641522348207376583379547");

        // Calculate phi
        BigInteger phiN = d.divide(e);

        // Factorize n
        BigInteger[] factors = factorizeN(n);
        if (factors != null) {
            BigInteger p = factors[0];
            BigInteger q = factors[1];

            // Print factors p and q
            System.out.println("Factorization of n:");
            System.out.println("p: " + p);
            System.out.println("q: " + q);
        } else {
            System.out.println("Unable to factorize n.");
        }
    }

    // Method to factorize n into prime factors p and q
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
        return null; // Return null if no factors found
    }

    // Method to compute square root of a BigInteger
    private static BigInteger sqrt(BigInteger x) {
        BigInteger sqrt = x;
        while (sqrt.multiply(sqrt).compareTo(x) > 0) {
            sqrt = sqrt.add(x.divide(sqrt)).shiftRight(1);
        }
        return sqrt;
    }
}
