package com.example.authblock.api;

import com.example.authblock.InfoAccessoSito;
import com.example.authblock.InfoAccessoUtente;
import com.example.authblock.chain.AuthBlockChain;
import com.example.authblock.cryptography.UtilsCrypto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


//NOTA: dovremmo cifrare anche i return
@RestController
@RequestMapping(value = "/api", method = POST)
public class AuthBlockAPI {

    @PostMapping(value = "/logout", produces = "application/json")
    public @ResponseBody String logout(@RequestBody String body) throws Exception {
        JSONObject object = new JSONObject(body);
        String indirizzoSito = UtilsCrypto.checkData(object.getString("sito"), object.getString("sitoHmac"));
        System.out.println("api ind sito:" + indirizzoSito);
        String indirizzoUtente = UtilsCrypto.checkData(object.getString("utente"), object.getString("utenteHmac"));
        System.out.println("api ind utente:" + indirizzoUtente);

        new AuthBlockChain().insertLogout(indirizzoSito,indirizzoUtente);
        return "{result:ok}";
    }

    @PostMapping(value = "/checkUser", produces = "application/json")
    public @ResponseBody String checkUser(@RequestBody String body) throws Exception {
        JSONObject object = new JSONObject(body);
        boolean flag = new AuthBlockChain()
                .checkUser(UtilsCrypto.checkData(object.getString("indirizzoSito"), object.getString("indirizzoSitoHmac")),
                        UtilsCrypto.checkData(object.getString("indirizzoUtente"), object.getString("indirizzoUtenteHmac")));
        if(flag)
            return "{result:true}";
        else return "{result:false}";
    }

    @PostMapping(value = "", produces = "application/json")
    public @ResponseBody String insertAccesso(@RequestBody String body, @RequestParam("newUser") boolean newUser) throws Exception {

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
                    .setUrl(UtilsCrypto.checkData(data.getString("url"), hmac.getString("url"))).build();
        } catch (Exception e) {
            return "{field:\"user data\"}";
        }

        //controllo oraLogin, logout, username, useragent e ip
        InfoAccessoSito infoAccessoSito;
        try {
            if(newUser)
                infoAccessoSito = new InfoAccessoSito.InfoAccessoSitoBuilder()
                    .setUsernameUtente(UtilsCrypto.checkData(data.getString("username"), hmac.getString("username")))
                    .setUserAgent(UtilsCrypto.checkData(data.getString("userAgent"), hmac.getString("userAgent")))
                    .setIpAddress(UtilsCrypto.checkData(data.getString("ipAddress"), hmac.getString("ipAddress"))).build();
            else
                infoAccessoSito = new InfoAccessoSito.InfoAccessoSitoBuilder()
                        .setUserAgent(UtilsCrypto.checkData(data.getString("userAgent"), hmac.getString("userAgent")))
                        .setIpAddress(UtilsCrypto.checkData(data.getString("ipAddress"), hmac.getString("ipAddress"))).build();
        } catch (Exception e) {
            return "{field:\"data site\"}";
        }

        //salvo i dati in blockchain
        try{
            System.out.println(newUser);
            System.out.println(infoAccessoSito);
            System.out.println(infoAccessoUtente);

            if(newUser)
                new AuthBlockChain().insertNewUser(indirizzoSito,indirizzoUtente, infoAccessoSito, infoAccessoUtente);
            else
                new AuthBlockChain().insertAccesso(indirizzoSito, indirizzoUtente, infoAccessoSito, infoAccessoUtente);
        }catch (Exception e){
            System.out.println(e);
            return "{error:\"insert accesso failed\"}";
        }
        return "{result:ok}";
    }
}
