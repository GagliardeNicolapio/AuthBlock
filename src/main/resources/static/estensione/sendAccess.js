
function insertVoto(){


    var myContract = new web3.eth.Contract(abi, addressContract, {from: account, gasPrice:'2000000', gas:1000000, data:bin})
    let url = location.href;
    myContract.methods.insertAccesso(url).send({value:500000000000000}).then((receipt)=>{alert("sito salvato")})
}

console.log("account da sendaccess: "+account)
if(typeof account === 'undefined') {
    var account;
    getAccount(insertVoto)

}else{
    insertVoto()
}