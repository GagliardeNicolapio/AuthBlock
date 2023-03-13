
function insertMedia(){
  /*  var web3 = new Web3(window.ethereum);
    web3.setProvider(window.ethereum)
*/
    console.log("account da insertmedia: "+account)

    var myContract = new web3.eth.Contract(abi, addressContract, {from: account, gasPrice:'2000000', gas:1000000, data:bin})

    myContract.methods.getMedia().call().then((receipt)=>{
        alert('La media di questo sito è: '+receipt/1000)
    })
}

console.log("account da getmaedia: "+account)
if(typeof account === 'undefined') {
    var account;
    getAccount(insertMedia)

}else{
    insertMedia()
}