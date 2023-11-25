/**
 * Let the following RSA key:
 * k1 = {n1=837210799, e1=7, d1=478341751}
 * Show how the modulus can be factored given the private key and find the private
 * exponent for the following key:
 * k2 = {n1=837210799, e2=17, d2=?}
 */

import java.math.BigInteger;

public class RSA_Viete_Modulus_Factoring {

    // Recursively searches for the square root of 'a' in interval [left, right]
    private static BigInteger NaiveSquareRootSearch(BigInteger a, BigInteger left, BigInteger right) {
        // Fix root as the arithmetic mean of left and right
        BigInteger root = left.add(right).shiftRight(1);
        // If the root is not between [root, root+1],
        // it's not an integer and root is our best integer approximation
        if (!((root.pow(2).compareTo(a) == -1)
                && (root.add(BigInteger.ONE).pow(2).compareTo(a) == 1))) {
            if (root.pow(2).compareTo(a) == -1)
                root = NaiveSquareRootSearch(a, root, right);
            if (root.pow(2).compareTo(a) == 1)
                root = NaiveSquareRootSearch(a, left, root);
        }
        return root;
    }

    // Computes the square root of a BigInteger 'a'
    public static BigInteger SquareRoot(BigInteger a) {
        return NaiveSquareRootSearch(a, BigInteger.ZERO, a);
    }

    // Method to perform factorization attack and reveal private key components
    public static void private_exponent_reveals_factorization() {
        // Initialize known values
        BigInteger n1 = new BigInteger("837210799"); // = p * q
        BigInteger e1 = new BigInteger("7");
        BigInteger d1 = new BigInteger("478341751");
        BigInteger k;
        BigInteger pPlusQ;
        BigInteger delta;
        BigInteger radDelta;
        BigInteger x1;
        BigInteger x2;

        BigInteger one = new BigInteger("1");
        BigInteger four = new BigInteger("4");
        BigInteger two = new BigInteger("2");

        BigInteger e2 = new BigInteger("17");
        BigInteger phiN;
        BigInteger d2;

        // Compute 'k' as part of factorization: k=[(d*e-1)/n]
        k = ((d1.multiply(e1)).subtract(one)).divide(n1);
        System.out.println("Computed k is: " + k); // BigInteger casts 3.99 to an integer, aka 3

        k = k.add(one);
        System.out.println("Final k is: " + k);

        // Compute 'p + q' as part of factorization: p+q=(k(n1+1)-d1*e1+1)/k
        pPlusQ = (k.multiply(n1.add(one)).add(one).subtract(e1.multiply(d1))).divide(k);
        System.out.println("p + q = " + pPlusQ);

        // Compute 'delta' for further calculations
        delta = (pPlusQ.pow(2)).subtract(four.multiply(n1)); // a^2-4*a*c; a^2 -> pPlusQ^2; a*c -> n1
        System.out.println("delta = " + delta);

        // Compute square root of 'delta'
        radDelta = SquareRoot(delta);
        System.out.println("square root of delta = " + radDelta);

        // Calculate 'x1' and 'x2' representing potential factors 'p' and 'q'
        x1 = (pPlusQ.add(radDelta)).divide(two);
        x2 = (pPlusQ.subtract(radDelta)).divide(two);
        System.out.println("x1 = " + x1); // p
        System.out.println("x2 = " + x2); // q

        // Calculate Euler's totient function value 'phiN'
        phiN = (x1.subtract(one)).multiply((x2.subtract(one)));
        System.out.println("phi(n) = " + phiN);

        // Calculate the second private exponent 'd2' using 'e2' and 'phiN'
        d2 = e2.modInverse(phiN);
        System.out.println("The inverse of " + e2 + " mod " + phiN + " is: " + d2); // Second private exponent
    }

    public static void main(String[] args) {
        private_exponent_reveals_factorization();
    }
}
