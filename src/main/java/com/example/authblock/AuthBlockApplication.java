package com.example.authblock;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.web3j.utils.Bytes;


import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@SpringBootApplication
public class AuthBlockApplication {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static void main(String[] args) {

        SpringApplication.run(AuthBlockApplication.class, args);


    }

    private String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public  String hmacWithJava(String addr) throws NoSuchAlgorithmException, InvalidKeyException {
        String hmacSHA256Algorithm = "HmacSHA256";
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 1000000;
        Random random = new Random();
        String key = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), hmacSHA256Algorithm);
        Mac mac = Mac.getInstance(hmacSHA256Algorithm);
        mac.init(secretKeySpec);
        return bytesToHex(mac.doFinal(addr.getBytes()));
    }

}
