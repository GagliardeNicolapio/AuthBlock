package com.example.authblock.api;

import com.example.authblock.InfoAccessoSito;
import com.example.authblock.InfoAccessoUtente;
import com.example.authblock.chain.AuthBlockChain;
import com.example.authblock.cryptography.UtilsCrypto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import static org.springframework.web.bind.annotation.RequestMethod.POST;



@RestController
@RequestMapping(value = "/api", method = POST)
public class AuthBlockAPI {

    @PostMapping(value = "", produces = "application/json")
    public @ResponseBody String insertAccesso(@RequestBody String body) throws Exception {

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
            indirizzoSito = UtilsCrypto.checkData(indirizzi.getString("ethSite"), hmacIndirizzi.getString("ethSite"));
            indirizzoUtente = UtilsCrypto.checkData(indirizzi.getString("ethUser"), hmacIndirizzi.getString("ethUser"));
        } catch (Exception e) {
            return e.getMessage() + "{field:\"address ethereum\"}";
        }


        //controllo oraLogin, logout, url e creo struttura per l'utente
      InfoAccessoUtente infoAccessoUtente;
        try {
            infoAccessoUtente = new InfoAccessoUtente.InfoAccessoUtenteBuilder()
                    .setOraLogin(UtilsCrypto.checkData(data.getString("oraLogin"), hmac.getString("oraLogin")))
                    .setOraLogout(UtilsCrypto.checkData(data.getString("oraLogout"), hmac.getString("oraLogout")))
                    .setUrl(UtilsCrypto.checkData(data.getString("url"), hmac.getString("url"))).build();
        } catch (Exception e) {
            return "{field:\"user data\"}";
        }

        //controllo oraLogin, logout, username, useragent e ip
        InfoAccessoSito infoAccessoSito;
        try {
             infoAccessoSito = new InfoAccessoSito.InfoAccessoSitoBuilder()
                    .setOraLogin(UtilsCrypto.checkData(data.getString("oraLogin"), hmac.getString("oraLogin")))
                    .setOraLogout(UtilsCrypto.checkData(data.getString("oraLogout"), hmac.getString("oraLogout")))
                    .setUsernameUtente(UtilsCrypto.checkData(data.getString("username"), hmac.getString("username")))
                    .setUserAgent(UtilsCrypto.checkData(data.getString("userAgent"), hmac.getString("userAgent")))
                    .setIpAddress(UtilsCrypto.checkData(data.getString("ipAddress"), hmac.getString("ipAddress"))).build();
        } catch (Exception e) {
            return "{field:\"data site\"}";
        }

        //salvo i dati in blockchain
        try{
            new AuthBlockChain().insertAccesso(indirizzoSito, indirizzoUtente, infoAccessoSito, infoAccessoUtente);
        }catch (Exception e){
            return "{error:\"insert accesso failed\"}";
        }
        return "{result:ok}";
    }
}
