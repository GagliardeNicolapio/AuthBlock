pragma solidity ^0;
pragma experimental ABIEncoderV2;
import "@openzeppelin/contracts/utils/Strings.sol";


contract AuthBlockLite {
    uint media;
    uint divisore;

    struct InfoAccesso{
        string host;
        string oraLogin;//capire come non duplicare oraLogin e oreLogout
    }



    //associa a un indirizzo di un utente un array di struct che contengono le info dei siti visitati dall'utente
    mapping(address => InfoAccesso[]) infoAccessi;
    //associa a un indirizzo di un utente il voto che ha dato
    mapping(address => uint8) votoUtente;
    //inserisci voto
    function insertVoto(uint8 vot) public{
        uint prodotto;
        require(vot > 1," Il voto e' troppo basso ");
        require(vot < 6,"IL voto e' magiore di 5");
        if(votoUtente[msg.sender] != 0){
            //add(10+20);
            prodotto = media * divisore;
            prodotto -= votoUtente[msg.sender];
            media = (prodotto + vot)/divisore;
        }else{
            votoUtente[msg.sender] = vot;
            media = (media * divisore) + vot;
            divisore++;
            media /= divisore;
        }
    }
    // media
    function getMedia() public view returns(uint){
        return media;
    }
    //nota: passare le strutture come parametro è una cosa sperimentale, infatti bisogna usare pragma experimental ABIEncoderV2; e NON funziona
    function insertAccesso(address indirizzo, string[] memory _dataInfoAccesso) public payable{
        require(msg.value>=0.0005 ether, "error sender");
        // isAccount(indirizzo), "Error address");
        InfoAccesso memory infoAccesso = InfoAccesso(_dataInfoAccesso[0],_dataInfoAccesso[1]);
        infoAccessi[indirizzo].push(infoAccesso);
    }
    // prende l'indirizzo e torna l'array json di accessi associati a quell'indirizzo
    function getInfoAccesso(address indirizzo) public view returns(string memory){
        require(infoAccessi[indirizzo].length > 0, "non ha effettuato nessun accesso");
        string memory data = string.concat("[",accessoSitoToString(infoAccessi[indirizzo][0]));
        for(uint i=1; i<infoAccessi[indirizzo].length; i++){
            data = string.concat(data,",",accessoSitoToString(infoAccessi[indirizzo][i]));
        }
        data = string.concat(data,"]");
        return data;
    }
    //converte un InfoAccessoSito in json
    function accessoSitoToString(InfoAccesso memory _accesso) private pure returns(string memory){
        return string.concat('{oraLogin:"', _accesso.oraLogin,'", host:"',_accesso.host,'"}');
    }


}
