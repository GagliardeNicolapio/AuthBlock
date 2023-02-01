pragma solidity ^0;


contract AuthBlockFull{
    struct  InfoAccessoSito{
        string oraLogin; //ora login e logout dell'utente
        string oraLogout;
        string usernameUtente;
        string userAgentUtente; //info del browser utente
        string indirizzoIPUtente;
    }

    struct InfoAccessoUtente{
        string urlSito;
        string oraLogin;//capire come non duplicare oraLogin e oreLogout
        string oraLogout;
    }


    //associa a un indirizzo di un sito web un array di struct che contengono le info degli utenti che hanno fatto il login su
    //quel sito
    mapping(address => InfoAccessoSito[]) sitiWeb;

    //associa a un indirizzo di un utente un array di struct che contengono le info dei siti visitati dall'utente
    mapping(address => InfoAccessoUtente[]) utenti;

    function insertAccesso(address indirizzoSito, address indirizzoUtente, InfoAccessoSito memory infoAccessoSito, InfoAccessoUtente memory infoAccessoUtente) public{
        require(isAccount(indirizzoSito) && isAccount(indirizzoUtente));
        sitiWeb[indirizzoSito].push(infoAccessoSito);
        utenti[indirizzoUtente].push(infoAccessoUtente);
    }

    function isAccount(address addr) private view returns (bool) {
        uint size;
        assembly { size := extcodesize(addr) }
        return size <= 0;
    }
}