import java.math.BigInteger;

/**
 * Factorizes the given integer 'n' known to be a power of a prime number
 * and estimates the expected number of steps to factor an integer of this form.
 * n = 141212165590455... (the large integer)
 */
public class PrimePowerFactorization {

    public static void main(String[] args) {

        BigInteger n = new BigInteger("14121216559045592723913725470284552915893297299545955512586695122770931673525642809374899750759599902194861123590215515956690880367223678270178015326064870241064451357668006100227147231177891238940152788700404344528460044850936426758850098076585795411392720202615259916568029436599814044031229151775310358906532007112584154431330139440890658043062963132741585343704418452606718651246455700938755220043301408176314160348698905378882614336939787183615667314218625753419259203124994887398592090289570466328291725708474859718918318673622960");

        // Loop to check root operations from square root to higher roots
        for (int root = 2; root <= 10; root++) {
            BigInteger rootValue = nthRoot(n, root);
            if (rootValue.pow(root).equals(n)) {
                System.out.println("n is a " + root + "th power of a prime number.");
                System.out.println("Prime root: " + rootValue);
                return;
            }
        }

        System.out.println("Unable to determine if n is a power of a prime number within reasonable computation time.");
    }

    // Method to compute nth root of a BigInteger
    private static BigInteger nthRoot(BigInteger x, int n) {
        BigInteger low = BigInteger.ZERO;
        BigInteger high = x.max(BigInteger.ONE);
        BigInteger result = BigInteger.ZERO;

        // Binary search for the nth root
        while (low.compareTo(high) <= 0) {
            BigInteger mid = low.add(high).divide(BigInteger.valueOf(2));
            if (mid.pow(n).compareTo(x) <= 0) {
                result = mid;
                low = mid.add(BigInteger.ONE);
            } else {
                high = mid.subtract(BigInteger.ONE);
            }
        }

        return result;
    }
}
