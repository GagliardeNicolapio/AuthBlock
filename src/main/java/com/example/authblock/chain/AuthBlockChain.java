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

    private static final String addressContract = "0x82e2D7CC6eBf64C7C702932273216174848f2957";
    private static final Credentials credentials = Credentials.create("76db681dd18e4e0f8cb78d538823a6333cf24bd3add56d16a8c33f04e970fb12");
    private static Contracts_AuthBlockFull_sol_AuthBlockFull contract;
    private static final String url = "http://172.18.80.1:7545";


    public AuthBlockChain(){
        contract = Contracts_AuthBlockFull_sol_AuthBlockFull.load(addressContract, Web3j.build(new HttpService(url)), credentials,new DefaultGasProvider());
    }

    public boolean checkUser(String indirizzoSito, String indirizzoUtente) throws Exception {
        return contract.checkUser(indirizzoSito,indirizzoUtente).send();
    }

    private String readIdFromMap(String key) throws IOException {
        HashMap<String, String> hashMap = readMap();
        System.out.println("key: "+key.toLowerCase());
        String value = hashMap.get(key.toLowerCase());
        return value;
    }

    public void insertLogout(String indirizzoSito, String indirizzoUtente) throws Exception {
        System.out.println("leggo da map: "+ indirizzoSito+","+indirizzoUtente);
        String id[] = readIdFromMap(indirizzoSito+","+indirizzoUtente).split(",");

        contract.insertLogout(indirizzoSito,indirizzoUtente, new BigInteger(id[0]), new BigInteger(id[1])).send();
    }

    public void insertAccesso(String indirizzoSito, String indirizzoUtente, InfoAccessoSito infoAccessoSito, InfoAccessoUtente infoAccessoUtente) throws Exception {
        for(Log log :  contract.insertAccesso(indirizzoSito, indirizzoUtente, infoAccessoSito.getData(), infoAccessoUtente.getData()).send().getLogs()){
            Contracts_AuthBlockFull_sol_AuthBlockFull.InserimentoAccessoFattoEventResponse response = contract.getInserimentoAccessoFattoEvents(log);
            String key = response.indirizzoSito + "," + response.indirizzoUtente;
            String value = response.idAccessoSito + "," + response.idAccessoUtente;
            System.out.println("inserisco insertAccesso: "+ key+"   "+ value);
            insertInMap(key, value);
        }
    }

    private void insertInMap(String key, String value) throws IOException{
        HashMap<String,String> map = readMap();
        map.put(key.toLowerCase(),value.toLowerCase());
        writeMap(map);
    }

    private HashMap<String, String> readMap() throws IOException{
        HashMap<String, String> hashMap;
        try {

            FileInputStream inputStream = new FileInputStream("src/main/java/com/example/authblock/mapLogoutRepository.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            hashMap = (HashMap<String, String>) objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();
        }catch (ClassNotFoundException | EOFException e ){
            hashMap = new HashMap<>();
        }
        return hashMap;
    }

    private void writeMap(HashMap<String,String> map) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/com/example/authblock/mapLogoutRepository.txt");
        ObjectOutputStream objectOutputStream =  new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(map);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public void insertNewUser(String indirizzoSito, String indirizzoUtente, InfoAccessoSito infoAccessoSito, InfoAccessoUtente infoAccessoUtente) throws Exception {
        System.out.println("insetnewuser");

       for(Log log : contract.insertUser(indirizzoSito, indirizzoUtente, infoAccessoSito.getData(), infoAccessoUtente.getData()).send().getLogs()){
          Contracts_AuthBlockFull_sol_AuthBlockFull.InserimentoAccessoFattoEventResponse response = contract.getInserimentoAccessoFattoEvents(log);
          String key = response.indirizzoSito + "," + response.indirizzoUtente;
          String value = response.idAccessoSito + "," + response.idAccessoUtente;
           System.out.println("inserisco insertNewUser: "+ key+"   "+ value);

           insertInMap(key, value);
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
