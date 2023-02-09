package com.example.authblock.chain;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class UtilsChain {

    public static String secondsToStringDate(String seconds){
        long millis =  Integer.parseInt(seconds) * 1000L;
        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE d MMMM yyyy, h:mm", Locale.ITALIAN);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedDate = sdf.format(date);
        System.out.println(formattedDate);
        return formattedDate;
    }

    public static String truncateAddress(String address){
        return address.substring(0,5)+"..."+address.substring(address.length()-5);
    }

    /* input : Indirizzo
     * Uscita: vero o falso
     * scopo: convalidare un checksum un indirizzo Ethereum
     * Procedura di checksum effettiva:
     * Se la i-esima cifra dell'Indirizzo è una lettera (es. è una di abcdef) stampala in maiuscolo
     * se l'iesimo bit dell'hash dell'indirizzo (in forma binaria) è 1
     * altrimenti stampalo in minuscolo.
     *
     * URL di riferimento: https://github.com/ethereum/EIPs/issues/55
     */


    public static boolean isAddress(String addr){
        //Print for testing purpose and more verbose output
        System.out.println("Incoming Address " + addr);

        // Per prima cosa dobbiamo verificare che l'indirizzo abbia un valore compreso tra 0-9a-fA-F
        String regex = "^0x[0-9a-fA-F]{40}$";
        if(!addr.matches(regex))
        {
            return false;
        }

        //per recuperare la parte dopo 0x
        String subAddr = addr.substring(2);
        //Rendilo all'indirizzo originale minuscolo
        String subAddrLower = subAddr.toLowerCase();

        //Print for testing purpose and more verbose output
        System.out.println("Fetched Original Address " + subAddrLower);

        // se il passaggio precedente viene convalidato, testeremo la parte del checksum

        // Crea un hash SHA3256 (Keccak-256)
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256();
        digestSHA3.update(subAddrLower.getBytes());
        String digestMessage = Hex.toHexString(digestSHA3.digest());

        //Print for testing purpose and more verbose output
        System.out.println("Hex String " + digestMessage);

        /* Verifica che ogni lettera sia maiuscola o meno
         * se è in maiuscolo, la corrispondente posizione binaria dell'indirizzo con hash
         * dovrebbe essere 1, ovvero la lettera digest del messaggio dovrebbe essere più getter di 7
         * poiché 7 è l'ultima cifra esadecimale che inizia con 0 in binario
         * il resto di tutti gli 8 a f inizia con 1
         */

        for(short i=0 ;i < subAddr.length();i++)
        {
            if(subAddr.charAt(i)>=65 && subAddr.charAt(i)<=91)
            {

                System.out.println("Position Upper " + (subAddr.charAt(i)) );
                System.out.println("Position digest " + (digestMessage.charAt(i)));

                String ss = Character.toString(digestMessage.charAt(i));
                if(!(Integer.parseInt(ss,16) > 7 ))
                {
                    return false;
                }
            }
        }

        return true;
    }
}
