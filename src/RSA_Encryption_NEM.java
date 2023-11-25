import java.math.BigInteger;

/**
 * Given the RSA encryption with the modulus and public exponent, find the encrypted message assuming
 * encryption was performed without padding.
 *
 * n = 116782527189977854790438768385081709656422702999575364741417649694317942515282871768071542694130275288528126673794181637674266563973912841859939833055367943073
 * e = 65537
 * m = 12345678901234567890
 *
 * RSA encryption formula (without padding): c = m^e mod n
 *
 * Given the public key components (n, e), and a message m, find the encrypted message c using the RSA encryption formula.
 */
public class RSA_Encryption_NEM {

    public static void main(String[] args) {

        BigInteger n = new BigInteger("116782527189977854790438768385081709656422702999575364741417649694317942515282871768071542694130275288528126673794181637674266563973912841859939833055367943073");
        BigInteger e = new BigInteger("65537");
        BigInteger m = new BigInteger("12345678901234567890");

        BigInteger encryptedMessage = m.modPow(e, n);

        System.out.println("Encrypted message (c): " + encryptedMessage);
    }
}
