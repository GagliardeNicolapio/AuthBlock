// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0;
pragma experimental ABIEncoderV2;
import "@openzeppelin/contracts/access/Ownable.sol";
import "@openzeppelin/contracts/utils/Strings.sol";

/******************************************************************************************************************
*   AuthBlockFull.sol
*
*   Questo contratto serve ad AuthBlock per implementare il servizio "completo"
*   WORKFLOW:
*       - Il sito cliente di AuthBlock, cioè RoachForLife, deve registrare un nuovo utente X
*       - RoachForLife comunica i dati del nuovo utente ad AuthBlock usando le REST API
*       - AuthBlock invoca il metodo insertUser che:
*           - salva l'address e la username del nuovo utente in utentiDeiSiti
*           - salva la struct InfoAccessoSito in infoPerSitiWeb
*           - salva la struct InfoAccessoUtente in infoPerUtenti
*
*       - L'utente X vuole loggarsi a RoachForLife
*       - RoachForLife, utilizzando le REST API, invoca il metodo checkUser per controllare se l'utente è registrato oppure no
*       - Se l'utente X è registrato, RoachForLife invoca insertAccesso, tramite REST API
*
*
*NOTE: passare le strutture come parametro è una cosa sperimentale, infatti bisogna usare pragma experimental ABIEncoderV2; e NON funziona
*
******************************************************************************************************************/

contract AuthBlockFull is Ownable{

    event InserimentoAccessoFatto(address indirizzoSito, address indirizzoUtente ,uint idAccessoSito, uint idAccessoUtente);


    //informazioni riguardo gli utenti del sito
    struct  InfoAccessoSito{
        uint oraLogin;
        uint oraLogout;
        string usernameUtente;
        string userAgentUtente;
        string indirizzoIPUtente;
    }

    //informazioni riguardo i siti visitati da un utente
    struct InfoAccessoUtente{
        string urlSito;
        uint oraLogin;
        uint oraLogout;
    }


    //associa a un indirizzo di un sito web un array di struct che contengono le info degli utenti che hanno fatto il login su
    //quel sito
    mapping(address => InfoAccessoSito[]) infoPerSitiWeb;

    //a ogni indirizzo di un sito, associa una mappa.
    //questa seconda mappa contiene gli indirizzi degli utenti di quel determinato sito
    //ogni indirizzo utente è collegata una string che rappresenta la username
    mapping(address => mapping(address => string)) utentiDeiSiti;

    //associa a un indirizzo di un utente un array di struct che contengono le info dei siti visitati dall'utente
    mapping(address => InfoAccessoUtente[]) infoPerUtenti;


/*********************************************************************************************************************************************++
Metodi invocabili solo da AuthBlock
*************************************************************************************************************************************************/

    function insertLogout(address _indirizzoSito, address _indirizzoUtente, uint idInfoSito, uint idInfoUtente) public onlyOwner{
        require(idInfoSito < infoPerSitiWeb[_indirizzoSito].length);
        require(idInfoUtente < infoPerUtenti[_indirizzoUtente].length);
        require(infoPerUtenti[_indirizzoUtente][idInfoUtente].oraLogout == 0);
        require(infoPerSitiWeb[_indirizzoSito][idInfoSito].oraLogout == 0);

        uint logout = block.timestamp;
        infoPerSitiWeb[_indirizzoSito][idInfoSito].oraLogout = logout;
        infoPerUtenti[_indirizzoUtente][idInfoUtente].oraLogout = logout;
    }

    //Da invocare quando un sito vuole registrare un nuovo utente
    //inserisce un nuovo utente con indirizzo _indirizzoUtente del sito _indirizzoSito
    function insertUser(address _indirizzoSito, address _indirizzoUtente, string[] memory _dataInfoAccessoSito, string[] memory _dataInfoAccessoUtente) public onlyOwner{
        require(bytes(_dataInfoAccessoSito[2]).length > 0, "username non valida");
        // require(isAccount(indirizzoSito) && isAccount(indirizzoUtente), "Error address");
        utentiDeiSiti[_indirizzoSito][_indirizzoUtente] = _dataInfoAccessoSito[0];
        insertAccesso(_indirizzoSito, _indirizzoUtente, _dataInfoAccessoSito, _dataInfoAccessoUtente);
    }

    //Da invocare quando un sito vuole registrare una nuova login
    //inserisce i dati della login
    function insertAccesso(address _indirizzoSito, address _indirizzoUtente, string[] memory _dataInfoAccessoSito, string[] memory _dataInfoAccessoUtente) public onlyOwner{
        // require(isAccount(indirizzoSito) && isAccount(indirizzoUtente), "Error address");
        uint loginDate = block.timestamp;
        InfoAccessoSito memory sito = InfoAccessoSito(loginDate, 0,utentiDeiSiti[_indirizzoSito][_indirizzoUtente],_dataInfoAccessoSito[1],_dataInfoAccessoSito[2]);
        InfoAccessoUtente memory utente = InfoAccessoUtente(_dataInfoAccessoUtente[0],loginDate, 0);
        infoPerSitiWeb[_indirizzoSito].push(sito);
        infoPerUtenti[_indirizzoUtente].push(utente);
        emit InserimentoAccessoFatto(_indirizzoSito, _indirizzoUtente, infoPerSitiWeb[_indirizzoSito].length-1, infoPerUtenti[_indirizzoUtente].length-1);
        //return (infoPerSitiWeb[_indirizzoSito].length-1, infoPerUtenti[_indirizzoUtente].length-1);
       // return string.concat("(",Strings.toString(infoPerSitiWeb[_indirizzoSito].length-1),",",Strings.toString(infoPerUtenti[_indirizzoUtente].length-1),")");
    }

    //dato un sito ritorna l'i-esimo accesso in formato json
    function getInfoAccessoSito(address indirizzoSito, uint accessNumber) public view onlyOwner returns(string memory){
        InfoAccessoSito memory accesso = infoPerSitiWeb[indirizzoSito][accessNumber];
        return accessoSitoToString(accesso);
    }

    function getInfoAccessoUtente(address indirizzoUtente, uint accessNumber) public view onlyOwner returns(string memory){
        InfoAccessoUtente memory accesso = infoPerUtenti[indirizzoUtente][accessNumber];
        return accessoUtenteToString(accesso);
    }

    //ritorna gli accessi InfoAccessoSito dall'accesso start-esimo al end-esimo in array json
    /*function getInfoAccessoSitoRange(address indirizzoSito, uint start, uint end) public view onlyOwner returns(string memory){
        require(start < end, "start non e' minore di end");
        require(end <= infoPerSitiWeb[indirizzoSito].length, "end e' maggiore di lenght");

        string memory data = string.concat("[",accessoSitoToString(infoPerSitiWeb[indirizzoSito][start]));
        for(uint i=start+1; i<end; i++){
            data = string.concat(data,",",accessoSitoToString(infoPerSitiWeb[indirizzoSito][i]));
        }
        data = string.concat(data,"]");
        return data;
    }*/

    //ritorna gli accessi InfoAccessoUtente dall'accesso start-esimo al end-esimo in array json
    /*function getInfoAccessoUtentiRange(address indirizzoUtente, uint start, uint end) public view onlyOwner returns(string memory){
        require(start < end, "start non e' minore di end");
        require(end <= infoPerUtenti[indirizzoUtente].length, "end e' maggiore di lenght");

        string memory data = string.concat("[",accessoUtenteToString(infoPerUtenti[indirizzoUtente][start]));
        for(uint i=start+1; i<end; i++){
            data = string.concat(data,",",accessoUtenteToString(infoPerUtenti[indirizzoUtente][i]));
        }
        data = string.concat(data,"]");
        return data;
    }*/


  /*********************************************************************************************************************************************************
Metodi invocabili da qualsiasi account/contratto
Permottono a un utente di creare una propria dashboard
*********************************************************************************************************************************************************/



    //per il pagamento del servizio full
    event PagamentoRicevuto(address indirizzoSito);
    receive() external payable{
        require(msg.value >= 1 ether, "Bisogna pagare 1 ether");
        emit PagamentoRicevuto(msg.sender);
    }


    //ritorna il numero di accessi al sito
    function getNumberAccessiSito(address indirizzoSito) public view returns(uint){
        return infoPerSitiWeb[indirizzoSito].length;
    }

    //ritorna il numero accessi effettuati da indirizzoUtente
    function getNumberAccessiUtente(address indirizzoUtente) public view returns(uint){
        return infoPerUtenti[indirizzoUtente].length;
    }

    //ritorna true se un indirizzo appartiene a un sito o a un utente
    function isPresent(address indirizzo) public view returns(bool){
        return infoPerSitiWeb[indirizzo].length > 0 || infoPerUtenti[indirizzo].length > 0;
    }

    //ritorna true se _indirizzoSito ha come utente _indirizzoUtente
    function checkUser(address _indirizzoSito, address _indirizzoUtente) public view returns(bool){
        return bytes(utentiDeiSiti[_indirizzoSito][_indirizzoUtente]).length > 0;
    }


/*********************************************************************************************************************************************************
    Metodi privati
*********************************************************************************************************************************************************/
    //converte un InfoAccessoUtente in json
    function accessoUtenteToString(InfoAccessoUtente memory _accesso) private pure returns(string memory){
        return string.concat('{urlSito:"', _accesso.urlSito, '",oraLogin:"', Strings.toString(_accesso.oraLogin),'",oraLogout:"', Strings.toString(_accesso.oraLogout),'"}');
    }

    //converte un InfoAccessoSito in json
    function accessoSitoToString(InfoAccessoSito memory _accesso) private pure returns(string memory){
        return string.concat('{oraLogin:"', Strings.toString(_accesso.oraLogin),'", oraLogout:"', Strings.toString(_accesso.oraLogout), '", username:"',
            _accesso.usernameUtente,'", userAgent:"', _accesso.userAgentUtente,'", ipAddress:"', _accesso.indirizzoIPUtente,'"}');
    }



    /*function isAccount(address addr) private view returns (bool) {
        uint size;
        assembly { size := extcodesize(addr) }
        return size <= 0;
    }*/
}