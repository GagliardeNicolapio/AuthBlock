package com.example.authblock.chain;

import com.example.authblock.InfoAccessoSito;
import com.example.authblock.InfoAccessoUtente;
import jnr.ffi.annotations.In;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.protocol.core.methods.response.Log;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class AuthBlockChain {

    private static final String addressContract = "0x4F024F8b38EB7620525340A4F85342a4bfC729a2";
    private static final Credentials credentials = Credentials.create("76db681dd18e4e0f8cb78d538823a6333cf24bd3add56d16a8c33f04e970fb12");
    private static Contracts_AuthBlockFull_sol_AuthBlockFull contract;
    private static final String url = "http://172.18.80.1:7545";


    public AuthBlockChain(){
        contract = Contracts_AuthBlockFull_sol_AuthBlockFull.load(addressContract, Web3j.build(new HttpService(url)), credentials,new DefaultGasProvider());
    }

    public boolean checkUser(String indirizzoSito, String indirizzoUtente) throws Exception {
        return contract.checkUser(indirizzoSito,indirizzoUtente).send();
    }


    public void insertLogout(String indirizzoSito, String indirizzoUtente) throws Exception {
        System.out.println("leggo da map: "+ indirizzoSito+","+indirizzoUtente);
        String id[] = MapLogoutDAO.readIdFromMap(indirizzoSito+","+indirizzoUtente).split(",");

        contract.insertLogout(indirizzoSito,indirizzoUtente, new BigInteger(id[0]), new BigInteger(id[1])).send();
    }

    public void insertAccesso(String indirizzoSito, String indirizzoUtente, InfoAccessoSito infoAccessoSito, InfoAccessoUtente infoAccessoUtente) throws Exception {
        for(Log log :  contract.insertAccesso(indirizzoSito, indirizzoUtente, infoAccessoSito.getData(), infoAccessoUtente.getData()).send().getLogs()){
            Contracts_AuthBlockFull_sol_AuthBlockFull.InserimentoAccessoFattoEventResponse response = contract.getInserimentoAccessoFattoEvents(log);
            String key = response.indirizzoSito + "," + response.indirizzoUtente;
            String value = response.idAccessoSito + "," + response.idAccessoUtente;
            System.out.println("inserisco insertAccesso: "+ key+"   "+ value);
            MapLogoutDAO.insertInMap(key, value);
        }
    }

    public void insertNewUser(String indirizzoSito, String indirizzoUtente, InfoAccessoSito infoAccessoSito, InfoAccessoUtente infoAccessoUtente) throws Exception {
        System.out.println("insetnewuser");

       for(Log log : contract.insertUser(indirizzoSito, indirizzoUtente, infoAccessoSito.getData(), infoAccessoUtente.getData()).send().getLogs()){
          Contracts_AuthBlockFull_sol_AuthBlockFull.InserimentoAccessoFattoEventResponse response = contract.getInserimentoAccessoFattoEvents(log);
          String key = response.indirizzoSito + "," + response.indirizzoUtente;
          String value = response.idAccessoSito + "," + response.idAccessoUtente;
           System.out.println("inserisco insertNewUser: "+ key+"   "+ value);

           MapLogoutDAO.insertInMap(key, value);
       }

    }

    public int getNumberAccessiSito(String indirizzoSito) throws Exception {
        return contract.getNumberAccessiSito(indirizzoSito).send().intValue();
    }

    public int getNumberAccessiUtente(String indirizzoUtente) throws Exception {
        return contract.getNumberAccessiUtente(indirizzoUtente).send().intValue();
    }

    public InfoAccessoSito getInfoAccessoSito(String indirizzoSito, int numAccess) throws Exception {
        String data =  contract.getInfoAccessoSito(indirizzoSito, BigInteger.valueOf(numAccess)).send();
        JSONObject object = new JSONObject(data);
        return new InfoAccessoSito.InfoAccessoSitoBuilder()
                .setUsernameUtente(object.getString("username"))
                .setUserAgent(object.getString("userAgent"))
                .setIpAddress(object.getString("ipAddress"))
                .buildWithLoginLogout(
                        UtilsChain.secondsToStringDate(object.getString("oraLogin")),
                        object.getString("oraLogout").equals("0") ? "0" : UtilsChain.secondsToStringDate(object.getString("oraLogout")));
    }

    public ArrayList<InfoAccessoSito> getInfoAccessoSito(String indirizzoSito, int start, int end) throws Exception {
        System.out.println("start:"+start);
        System.out.println("end:"+end);
        System.out.println("numAccess:"+getNumberAccessiSito(indirizzoSito));
        ArrayList<InfoAccessoSito> lista = new ArrayList<>();
        JSONArray array = new JSONArray(contract.getInfoAccessoSitoRange(indirizzoSito,BigInteger.valueOf(start),BigInteger.valueOf(end)).send());
        for (int i=0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            lista.add(new InfoAccessoSito.InfoAccessoSitoBuilder()
                    .setUsernameUtente(obj.getString("username"))
                    .setUserAgent(obj.getString("userAgent"))
                    .setIpAddress(obj.getString("ipAddress"))
                    .buildWithLoginLogout(
                            UtilsChain.secondsToStringDate(obj.getString("oraLogin")),
                            obj.getString("oraLogout").equals("0") ? "0" : UtilsChain.secondsToStringDate(obj.getString("oraLogout"))));
        }

        return lista;
    }

    public ArrayList<InfoAccessoUtente> getInfoAccessoUtente(String indirizzoUtente, int start, int end) throws Exception {
        System.out.println("start:"+ start);
        System.out.println("end: "+ end);
        System.out.println("num:"+ getNumberAccessiUtente(indirizzoUtente));


        ArrayList<InfoAccessoUtente> lista = new ArrayList<>();
        JSONArray array = new JSONArray(contract.getInfoAccessoUtentiRange(indirizzoUtente,BigInteger.valueOf(start),BigInteger.valueOf(end)).send());
        System.out.println(array);
        for (int i=0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            lista.add(new InfoAccessoUtente.InfoAccessoUtenteBuilder()
                    .setUrl(obj.getString("urlSito"))
                    .buildWithLoginLogout(
                            UtilsChain.secondsToStringDate(obj.getString("oraLogin")),
                            obj.getString("oraLogout").equals("0") ? "0" : UtilsChain.secondsToStringDate(obj.getString("oraLogout"))));
        }

        return lista;
    }

    public boolean isPresent(String indirizzo) throws Exception {
        return contract.isPresent(indirizzo).send();
    }
}
