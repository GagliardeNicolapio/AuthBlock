pragma solidity ^0;
pragma experimental ABIEncoderV2;

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


    //nota: passare le strutture come parametro è una cosa sperimentale, infatti bisogna usare pragma experimental ABIEncoderV2; e NON funziona
    function insertAccesso(address _indirizzoSito, address _indirizzoUtente, string[] memory _dataInfoAccessoSito, string[] memory _dataInfoAccessoUtente) public{
        // require(isAccount(indirizzoSito) && isAccount(indirizzoUtente), "Error address");
        InfoAccessoSito memory sito = InfoAccessoSito(_dataInfoAccessoSito[0],_dataInfoAccessoSito[1],_dataInfoAccessoSito[2],_dataInfoAccessoSito[3],_dataInfoAccessoSito[4]);
        InfoAccessoUtente memory utente = InfoAccessoUtente(_dataInfoAccessoUtente[0],_dataInfoAccessoUtente[2],_dataInfoAccessoUtente[2]);

        sitiWeb[_indirizzoSito].push(sito);
        utenti[_indirizzoUtente].push(utente);
    }

    function getNumberAccessiSito(address indirizzoSito) public view returns(uint){
        return sitiWeb[indirizzoSito].length;
    }

    function getInfoAccessoSito(address indirizzoSito, uint accessNumber) public view returns(string memory){
        InfoAccessoSito memory accesso = sitiWeb[indirizzoSito][accessNumber];
        return string(abi.encodePacked(accesso.oraLogin, accesso.oraLogout, accesso.usernameUtente, accesso.userAgentUtente, accesso.indirizzoIPUtente));
    //    return "{oraLogin:"+accesso.oraLogin+", oraLogout:"+accesso.oraLogout+", username:"+accesso.usernameUtente+", userAgent:"+accesso.userAgentUtente+", ipAddress:"+accesso.indirizzoIPUtente+"}";
    }

   /* function isAccount(address addr) private view returns (bool) {
        uint size;
        assembly { size := extcodesize(addr) }
        return size <= 0;
    }*/
}