package com.example.authblock.cryptography;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class UtilsCrypto {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private static String keyMAC="chiave";
    private static String algorithm = "HmacSHA256";


    public static String generatekey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        keyGen.init(256); // for example
        return bytesToHex(keyGen.generateKey().getEncoded());
    }

    public static void generateRSAKeys() throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        try(FileOutputStream fos = new FileOutputStream("public.key")){
            fos.write(publicKey.getEncoded());
        }
        try(FileOutputStream fos = new FileOutputStream("private.key")){
            fos.write(privateKey.getEncoded());
        }
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    private static String decryptRSA(String data) throws Exception {
        File privateKeyFile = new File("private.key");
        byte[] privateKeyBytes = Files.readAllBytes(privateKeyFile.toPath());

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] test = Base64.getDecoder().decode(data);
        byte[] decryptedMessageBytes = decryptCipher.doFinal(test);
        return new String(decryptedMessageBytes, StandardCharsets.UTF_8);
    }

    public static String checkData(String dataEnc, String hmacEnc) throws Exception {
        String dataDec = decryptRSA(dataEnc);
        if(!decryptRSA(hmacEnc).equals(hmac(dataDec)))
            throw new Exception("{error:\"hmac failed\"}");
        return dataDec;
    }

    public static String checkDataCrypt(String dataEnc, String hmacEnc) throws Exception {
        String dataDec = decryptRSA(dataEnc);
        if(!decryptRSA(hmacEnc).equals(hmac(dataDec)))
            throw new Exception("{error:\"hmac failed\"}");
        return dataEnc;
    }


    private static String hmac(String data) throws Exception {

        SecretKeySpec secretKeySpec = new SecretKeySpec(keyMAC.getBytes(), algorithm);
        Mac mac = Mac.getInstance(algorithm);
        mac.init(secretKeySpec);
        return bytesToHex(mac.doFinal(data.getBytes()));
    }

}
