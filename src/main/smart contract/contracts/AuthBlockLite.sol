pragma solidity ^0;
pragma experimental ABIEncoderV2;
import "@openzeppelin/contracts/utils/Strings.sol";
import "@openzeppelin/contracts/utils/math/SafeMath.sol";

contract AuthBlockLite {

    struct InfoAccesso{
        string host;
        uint oraLogin;
    }


    //associa a un indirizzo di un utente/sito web un array di struct InfoAccesso
    mapping(address => InfoAccesso[]) infoAccessi;


    uint sommaVoti;
    uint numeroVoti;

    //associa a un indirizzo di un utente/sito web il voto che ha dato
    mapping(address => uint8) voti;


    //inserisci voto
    function insertVoto(uint8 nuovoVoto) public{

        require(nuovoVoto >= 1," Il voto e' troppo basso ");
        require(nuovoVoto <= 5,"IL voto e' maggiore di 5");

        if(voti[msg.sender] != 0){
            sommaVoti = SafeMath.add(SafeMath.sub(sommaVoti, voti[msg.sender]), nuovoVoto);
        }else{
            sommaVoti = SafeMath.add(sommaVoti, nuovoVoto);
            numeroVoti = SafeMath.add(numeroVoti, 1);
        }
        voti[msg.sender] = nuovoVoto;
    }
    // media
    function getMedia() public view returns(uint){
        return SafeMath.div(SafeMath.mul(1000, sommaVoti), numeroVoti);
    }


    function insertAccesso(string memory host) public payable{
        require(msg.value>=0.0005 ether, "error sender");
        // isAccount(indirizzo), "Error address");
        InfoAccesso memory infoAccesso = InfoAccesso(host,block.timestamp);
        infoAccessi[msg.sender].push(infoAccesso);
    }

    function getNumAccessi() public view returns(uint){
        return infoAccessi[msg.sender].length;
    }

    // prende l'indirizzo e torna l'array json di accessi associati a quell'indirizzo
    function getInfoAccesso() public view returns(string memory){
        require(infoAccessi[msg.sender].length > 0, "nessun accesso trovato");
        string memory data = string.concat("[",accessoSitoToString(infoAccessi[msg.sender][0]));
        for(uint i=1; i<infoAccessi[msg.sender].length; i++){
            data = string.concat(data,",",accessoSitoToString(infoAccessi[msg.sender][i]));
        }
        data = string.concat(data,"]");
        return data;
    }

    //converte un InfoAccessoSito in json
    function accessoSitoToString(InfoAccesso memory _accesso) private pure returns(string memory){
        return string.concat('{"oraLogin":"', Strings.toString(_accesso.oraLogin),'", "host":"',_accesso.host,'"}');
    }


}
