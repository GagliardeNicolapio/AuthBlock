
function voto(){

    var myContract = new web3.eth.Contract(abi, addressContract, {from: account, gasPrice:'2000000', gas:1000000, data:bin})

    //da decommenmy
    myContract.methods.insertVoto(2).send({from:account}).then((receipt)=>{console.log('receipt insertvoto: '+receipt)})

    //test
    //let accesso = ['google.com','23:30']
    //myContract.methods.insertAccesso(account,accesso).send({from:account, value:700000000000000}).then(function(receipt){console.log('pippo'+receipt)})
}
console.log("prova")
if(typeof account === 'undefined') {
    console.log("prova if")

    var account;
    getAccount(voto)

}else{
    console.log("prova else")

    voto()
}


