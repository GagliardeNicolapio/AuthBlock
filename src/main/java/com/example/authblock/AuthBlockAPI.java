package com.example.authblock;

import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.web.bind.annotation.*;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;



@RestController
@RequestMapping(value = "/api", method = POST)
public class AuthBlockAPI {
    private String keyMAC="chiave";
    private String algorithm = "HmacSHA256";
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    @PostMapping(value = "", produces = "application/json")
    public @ResponseBody String insertAccesso(@RequestBody String body) throws Exception {
        ArrayList<String> keys = new ArrayList<>(Arrays.asList("oraLogin", "oraLogout", "username", "userAgent", "ipAddress"));
        System.out.println(body);
        JSONArray array = new JSONArray(body);
        JSONObject data = array.getJSONObject(0);
        JSONObject hmac = array.getJSONObject(1);


        for (String key : keys) {
            String fieldData = data.getString(key);
            String fieldHmac = hmac.getString(key);
            String decData =  decryptRSA(fieldData);
            System.out.println(fieldData+": "+decData);
            String decHmac = decryptRSA(fieldHmac);
            if(!hmac(decData).equals(decHmac))
                return "{error:\"hmac failed\"}";
        }







        return "{result:ok}";
    }


    private String hmac(String data) throws Exception {

        SecretKeySpec secretKeySpec = new SecretKeySpec(keyMAC.getBytes(), algorithm);
        Mac mac = Mac.getInstance(algorithm);
        mac.init(secretKeySpec);
        return bytesToHex(mac.doFinal(data.getBytes()));
    }

    private String decryptRSA(String data) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        File privateKeyFile = new File("private.key");
        byte[] privateKeyBytes = Files.readAllBytes(privateKeyFile.toPath());

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] test = Base64.getDecoder().decode(data);
        byte[] decryptedMessageBytes = decryptCipher.doFinal(test);//data.getBytes(StandardCharsets.UTF_8)
        return new String(decryptedMessageBytes, StandardCharsets.UTF_8);
    }

    public String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
