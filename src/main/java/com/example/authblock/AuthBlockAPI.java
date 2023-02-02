package com.example.authblock;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;



@RestController
@RequestMapping(value = "/api", method = POST)
public class AuthBlockAPI {
    private static final String addressContract = "0x5f147Ce595215eE9345D87bb8A0FA9f688B485B6";
    private String keyMAC="chiave";
    private String algorithm = "HmacSHA256";
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    @PostMapping(value = "", produces = "application/json")
    public @ResponseBody String insertAccesso(@RequestBody String body) throws Exception {
        ArrayList<String> keysAccessoSito = new ArrayList<>(Arrays.asList("oraLogin", "oraLogout", "username", "userAgent", "ipAddress"));
        ArrayList<String> keysAccessoUtente = new ArrayList<>(Arrays.asList("oraLogin", "oraLogout", "url"));

        JSONArray array = new JSONArray(body);

        //indirizzi ethereum
        JSONObject indirizzi = array.getJSONObject(0);

        //hmac degli indirizzi ethereum
        JSONObject hmacIndirizzi = array.getJSONObject(1);

        //oraLogin, logout, username, userAgent, ip, url
        JSONObject data = array.getJSONObject(2);

        //hmac di oraLogin, logout, username, userAgent, ip, url
        JSONObject hmac = array.getJSONObject(3);


        //controllo gli indirizzi
        String indirizzoSito;
        String indirizzoUtente;
        try {
            indirizzoSito = checkData(indirizzi.getString("ethSite"), hmacIndirizzi.getString("ethSite"));
            indirizzoUtente = checkData(indirizzi.getString("ethUser"), hmacIndirizzi.getString("ethUser"));
        } catch (Exception e) {
            return e.getMessage() + "{field:\"address ethereum\"}";
        }


        //controllo oraLogin, logout, url e creo struttura per l'utente
      InfoAccessoUtente infoAccessoUtente;
        try {
            infoAccessoUtente = new InfoAccessoUtente.InfoAccessoUtenteBuilder()
                    .setOraLogin(checkData(data.getString("oraLogin"), hmac.getString("oraLogin")))
                    .setOraLogout(checkData(data.getString("oraLogout"), hmac.getString("oraLogout")))
                    .setUrl(checkData(data.getString("url"), hmac.getString("url"))).build();
        } catch (Exception e) {
            return e.getMessage() + "{field:\"user data\"}";
        }

        //controllo oraLogin, logout, username, useragent e ip
        InfoAccessoSito infoAccessoSito;
        try {
             infoAccessoSito = new InfoAccessoSito.InfoAccessoSitoBuilder()
                    .setOraLogin(checkData(data.getString("oraLogin"), hmac.getString("oraLogin")))
                    .setOraLogout(checkData(data.getString("oraLogout"), hmac.getString("oraLogout")))
                    .setUsernameUtente(checkData(data.getString("username"), hmac.getString("username")))
                    .setUserAgent(checkData(data.getString("userAgent"), hmac.getString("userAgent")))
                    .setIpAddress(checkData(data.getString("ipAddress"), hmac.getString("ipAddress"))).build();
        } catch (Exception e) {
            return e.getMessage() + "{field:\"data site\"}";
        }

        //salvo i dati in blockchain
        //aCosaServeQuesta?
        //per il VM Exception while processing transaction: revert provare a commentare le require nel contratto .sol
        Credentials credentials = Credentials.create("856a0b665c53bc02366b0ec25d673bc835f02ea4ec6aa50fdc8676a9275bd988");
        System.out.println(new DefaultGasProvider().getGasLimit());
        Contracts_AuthBlockFull_sol_AuthBlockFull contract = Contracts_AuthBlockFull_sol_AuthBlockFull.load(addressContract, Web3j.build(new HttpService("http://172.19.204.23:7545")), credentials,new DefaultGasProvider());
        contract.insertAccesso(indirizzoSito, indirizzoUtente, infoAccessoSito.getData(), infoAccessoUtente.getData()).send();


        String x =  contract.getInfoAccessoSito(indirizzoSito, BigInteger.valueOf(0)).send();
        System.out.println(x);
        // contract.insertAccesso(indirizzoSito, indirizzoUtente, infoAccessoSito, infoAccessoUtente).send();
        return "{result:ok}";
    }


    private String checkData(String dataEnc, String hmacEnc) throws Exception {
        String dataDec = decryptRSA(dataEnc);
        if(!decryptRSA(hmacEnc).equals(hmac(dataDec)))
            throw new Exception("{error:\"hmac failed\"}");
        return dataDec;
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
