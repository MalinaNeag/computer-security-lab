import java.math.BigInteger;

/**
 * Given the RSA encryption below with the corresponding modulus and private exponent,
 * find the encrypted message assuming that encryption was performed without padding.
 *
 * n = 979962689108397953859713900466603422260018238402543509996330157732239715220798759847677388539456166671934288858674336773164440230108670065987733897844911073
 * d = 547176975721516212111078562595777253092776717184379103359014724642243452587318960552862049346313974697435390545918373131366499356297070096472309893679222257
 * m = 9876
 *
 * RSA encryption formula (without padding): c = m^d mod n
 *
 * Given the private key components (n, d), and a message m, find the encrypted message c using the RSA encryption formula.
 */
public class RSA_Encryption_NDM {

    public static void main(String[] args) {

        BigInteger n = new BigInteger("979962689108397953859713900466603422260018238402543509996330157732239715220798759847677388539456166671934288858674336773164440230108670065987733897844911073");
        BigInteger d = new BigInteger("547176975721516212111078562595777253092776717184379103359014724642243452587318960552862049346313974697435390545918373131366499356297070096472309893679222257");
        BigInteger m = new BigInteger("9876");

        BigInteger encryptedMessage = m.modPow(d, n);

        System.out.println("Encrypted message (c): " + encryptedMessage);
    }
}
