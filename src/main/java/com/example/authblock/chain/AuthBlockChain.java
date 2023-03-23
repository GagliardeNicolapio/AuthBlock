package com.example.authblock.chain;

import com.example.authblock.InfoAccessoSito;
import com.example.authblock.InfoAccessoUtente;
import com.example.authblock.cryptography.UtilsCrypto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.protocol.core.methods.response.Log;
import java.math.BigInteger;
import java.util.ArrayList;


public class AuthBlockChain {

    private static final String addressContract = "0x6677a85BDeCf4b2c32266c23c6c16Ad4AB321d90";
    private static final Credentials credentials = Credentials.create("02fbeb026fbef5304ca485c2ad7b0a9b81b31eb1f04a17546c4d54748f25cf62");
    public  Contracts_AuthBlockFull_sol_AuthBlockFull contract;
    private static final String url = "http://0.0.0.0:7545";


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


   /* public static final Event PAGAMENTO_EFFETTUATO_EVENT = new Event("PagamentoRicevuto",
         Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Int>(false) {}));
    private static final String INCREMENT_EVENT_HASH = EventEncoder.encode(PAGAMENTO_EFFETTUATO_EVENT);

    public void getPagamentoRicevutoEvents(){
        EthFilter filter = new EthFilter(
                DefaultBlockParameterName.EARLIEST, // From block 0
                DefaultBlockParameterName.LATEST,  // To latest
                "0x05eC8011d7B54129e0FeD376c4Cff4A91D9D2f15") // Unique Smart Contract
                .addSingleTopic(INCREMENT_EVENT_HASH);

        contract.pagamentoRicevutoEventFlowable(filter).subscribe(event ->{
            System.out.println("TEST EVENT");
            System.out.println(event.toString());
        });
    }*/


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
        System.out.println("insetnewuser"+ infoAccessoSito.getData()+"  "+ infoAccessoUtente.getData());

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
                .setUserAgent(UtilsCrypto.decryptRSA( object.getString("userAgent")))
                .setIpAddress(UtilsCrypto.decryptRSA( object.getString("ipAddress")))
                .buildWithLoginLogout(
                        UtilsChain.secondsToStringDate(object.getString("oraLogin")),
                        object.getString("oraLogout").equals("0") ? "0" : UtilsChain.secondsToStringDate(object.getString("oraLogout")));
    }

    public InfoAccessoUtente getInfoAccessoUtente(String indirizzoUtente, int numAccess) throws Exception {
        System.out.println("getinfoaccessoutente: "+indirizzoUtente+"  "+numAccess);
        String data =  contract.getInfoAccessoUtente(indirizzoUtente, BigInteger.valueOf(numAccess)).send();
        JSONObject obj = new JSONObject(data);
        return new InfoAccessoUtente.InfoAccessoUtenteBuilder()
                .setUrl(UtilsCrypto.decryptRSA(obj.getString("urlSito")))
                .buildWithLoginLogout(
                        UtilsChain.secondsToStringDate(obj.getString("oraLogin")),
                        obj.getString("oraLogout").equals("0") ? "0" : UtilsChain.secondsToStringDate(obj.getString("oraLogout")));
    }

   /* public ArrayList<InfoAccessoSito> getInfoAccessoSito(String indirizzoSito, int start, int end) throws Exception {
        System.out.println("start:"+start);
        System.out.println("end:"+end);
        System.out.println("numAccess:"+getNumberAccessiSito(indirizzoSito));
        ArrayList<InfoAccessoSito> lista = new ArrayList<>();
        JSONArray array = new JSONArray(contract.getInfoAccessoSitoRange(indirizzoSito,BigInteger.valueOf(start),BigInteger.valueOf(end)).send());
        for (int i=0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            lista.add(new InfoAccessoSito.InfoAccessoSitoBuilder()
                    .setUsernameUtente(obj.getString("username"))
                    .setUserAgent(UtilsCrypto.decryptRSA(obj.getString("userAgent")))
                    .setIpAddress(UtilsCrypto.decryptRSA(obj.getString("ipAddress")))
                    .buildWithLoginLogout(
                            UtilsChain.secondsToStringDate(obj.getString("oraLogin")),
                            obj.getString("oraLogout").equals("0") ? "0" : UtilsChain.secondsToStringDate(obj.getString("oraLogout"))));
        }

        return lista;
    }*/

    /*public ArrayList<InfoAccessoUtente> getInfoAccessoUtente(String indirizzoUtente, int start, int end) throws Exception {
        System.out.println("start:"+ start);
        System.out.println("end: "+ end);
        System.out.println("num:"+ getNumberAccessiUtente(indirizzoUtente));


        ArrayList<InfoAccessoUtente> lista = new ArrayList<>();
        JSONArray array = new JSONArray(contract.getInfoAccessoUtentiRange(indirizzoUtente,BigInteger.valueOf(start),BigInteger.valueOf(end)).send());
        System.out.println(array);
        for (int i=0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            lista.add(new InfoAccessoUtente.InfoAccessoUtenteBuilder()
                    .setUrl(UtilsCrypto.decryptRSA(obj.getString("urlSito")))
                    .buildWithLoginLogout(
                            UtilsChain.secondsToStringDate(obj.getString("oraLogin")),
                            obj.getString("oraLogout").equals("0") ? "0" : UtilsChain.secondsToStringDate(obj.getString("oraLogout"))));
        }

        return lista;
    }*/

    public boolean isPresent(String indirizzo) throws Exception {
        return contract.isPresent(indirizzo).send();
    }
}
