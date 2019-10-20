package com.temenos.gle.util;


/**
 *
 * @author vnikeshh
 *
 */
public class EncryptionDecryption {

    public static void main(String[] args) {
        final String secretKey = "Key";

        String originalString = "0160&Yhs&BNK@@USD&18/10/2019&7037689.77";
        String encryptedString = AES.encrypt(originalString, secretKey);
        String decryptedString = AES.decrypt(encryptedString, secretKey);

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
    }

}
