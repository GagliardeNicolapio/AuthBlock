
var account = "0x0caA08387Df40E4eD8EE1F1a0452398F3a9eb23E"
var myContract = new web3.eth.Contract(abi, addressContract, {from: account, gasPrice:'2000000', gas:1000000, data:bin})

//da decommenmy
var bodyTabella = document.getElementById("tabellaDati").getElementsByTagName("tbody")[0]
myContract.methods.getInfoAccesso().call({from:account}).then((receipt)=>{
    console.log('receipt insertvoto: '+receipt)
    var arrayJson = JSON.parse(receipt)
    for(let i = 0; i < arrayJson.length; i++) {
        let obj = arrayJson[i];
        console.log(obj.id);
        var newRow = bodyTabella.insertRow()
        var newCell = newRow.insertCell(0)
        var newText = document.createTextNode(obj.oraLogin)
        newCell.appendChild(newText)
    }
})