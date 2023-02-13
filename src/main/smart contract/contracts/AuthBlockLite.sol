pragma solidity ^0;
pragma experimental ABIEncoderV2;
import "@openzeppelin/contracts/utils/Strings.sol";
import "@openzeppelin/contracts/utils/math/SafeMath.sol";

contract AuthBlockLite {
    uint256 media;
    uint256 divisore;

    struct InfoAccesso{
        string host;
    }



    //associa a un indirizzo di un utente un array di struct che contengono le info dei siti visitati dall'utente
    mapping(address => InfoAccesso[]) infoAccessi;
    //associa a un indirizzo di un utente il voto che ha dato
    mapping(address => uint256) votoUtente;
    //inserisci voto
    function insertVoto(uint256 vot) public{
        uint256 prodotto;
        require(vot > 0," Il voto e' troppo basso ");
        require(vot < 6,"IL voto e' magiore di 5");
        if(votoUtente[msg.sender] != 0){
            //  prodotto = media * divisore;
            prodotto = SafeMath.mul(media,divisore);
            // prodotto -= votoUtente[msg.sender];
            prodotto = SafeMath.sub(prodotto,votoUtente[msg.sender]);
            // (prodotto + vot)/divisore;
            media = SafeMath.add(prodotto,vot);
            //divisore
            media = SafeMath.div(media,divisore);
        }else{
            votoUtente[msg.sender] = vot;
            //media = (media * divisore) + vot;
            media = SafeMath.mul(media,divisore);
            media = SafeMath.add(media,vot);
            //divisore++;
            divisore = SafeMath.add(divisore,1);
            //media /= divisore;
            media = SafeMath.div(media,divisore);
        }
    }
    // media
    function getMedia() public view returns(uint){
        return 56;
    }
    //nota: passare le strutture come parametro è una cosa sperimentale, infatti bisogna usare pragma experimental ABIEncoderV2; e NON funziona
    function insertAccesso(address indirizzo, string[] memory _accesso) public payable{
        require(msg.value>=0.0005 ether, "error sender");
        // isAccount(indirizzo), "Error address");
        InfoAccesso memory infoAccesso = InfoAccesso(_accesso);
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
