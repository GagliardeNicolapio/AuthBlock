package com.example.authblock.chain;

import com.example.authblock.InfoAccessoSito;
import com.example.authblock.InfoAccessoUtente;
import org.json.JSONArray;
import org.json.JSONObject;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;

public class AuthBlockChain {
    private static final String addressContract = "0x0d3416a8d40B4b15208Bb9807Cc31D68E5Abe68C";
    private static final Credentials credentials = Credentials.create("cf0c0f5f13fd3cd6efcca4289c19f642b937cb999a7809476d064a0b9c8d8261");
    private static Contracts_AuthBlockFull_sol_AuthBlockFull contract;
    private static final String url = "http://172.19.214.32:7545";

    public AuthBlockChain(){
        contract = Contracts_AuthBlockFull_sol_AuthBlockFull.load(addressContract, Web3j.build(new HttpService(url)), credentials,new DefaultGasProvider());
    }

    public boolean checkUser(String indirizzoSito, String indirizzoUtente) throws Exception {
        return contract.checkUser(indirizzoSito,indirizzoUtente).send();
    }

    public void insertAccesso(String indirizzoSito, String indirizzoUtente, InfoAccessoSito infoAccessoSito, InfoAccessoUtente infoAccessoUtente) throws Exception {
        contract.insertAccesso(indirizzoSito, indirizzoUtente, infoAccessoSito.getData(), infoAccessoUtente.getData()).send();
    }

    public void insertNewUser(String indirizzoSito, String indirizzoUtente, InfoAccessoSito infoAccessoSito, InfoAccessoUtente infoAccessoUtente) throws Exception {
        contract.insertUser(indirizzoSito, indirizzoUtente, infoAccessoSito.getData(), infoAccessoUtente.getData()).send();
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
                .setOraLogin(object.getString("oraLogin"))
                .setOraLogout(object.getString("oraLogout"))
                .setUsernameUtente(object.getString("username"))
                .setUserAgent(object.getString("userAgent"))
                .setIpAddress(object.getString("ipAddress")).build();
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
                    .setOraLogin(obj.getString("oraLogin"))
                    .setOraLogout(obj.getString("oraLogout"))
                    .setUsernameUtente(obj.getString("username"))
                    .setUserAgent(obj.getString("userAgent"))
                    .setIpAddress(obj.getString("ipAddress")).build());
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
                    .setOraLogin(obj.getString("oraLogin"))
                    .setOraLogout(obj.getString("oraLogout"))
                    .setUrl(obj.getString("urlSito")).build());
        }

        return lista;
    }

    public boolean isPresent(String indirizzo) throws Exception {
        return contract.isPresent(indirizzo).send();
    }
}
