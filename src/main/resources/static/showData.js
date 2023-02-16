var account;

async function getAccount(){

    if(window.web3){

        const selectedAccount = await window.ethereum
            . request({
                method:"eth_requestAccounts",
            })
            .then((accounts) => {
                account = accounts[0]
                console.log("aaaacc:"+account)

            })
            .catch(() => {
                alert("Nessun account selezionato")
                throw Error("Nessun account selezionato");
            });

    }else{alert("Devi installare MetaMask");}
}


getAccount().then(()=>{
    console.log("accouunt in show data "+account)
    var myContract = new web3.eth.Contract(abi, addressContract, {from: account, gasPrice:'2000000', gas:1000000, data:bin})

    myContract.methods.getNumAccessi().call().then((result)=>{
        if(result==0)
            alert("non ci sono dati da mostrare")
        else{
            document.getElementById("sitiVisitati").innerText = result
            var bodyTabella = document.getElementById("tabellaDati").getElementsByTagName("tbody")[0]
            myContract.methods.getInfoAccesso().call().then((receipt)=>{
                console.log('receipt get indo accesso: '+receipt)
                var arrayJson = JSON.parse(receipt)
                for(let i = 0; i < arrayJson.length; i++) {
                    let obj = arrayJson[i];
                    console.log(obj.id);
                    var newRow = bodyTabella.insertRow()

                    var newCell = newRow.insertCell(0)
                    var newText = document.createTextNode(new Date(obj.oraLogin * 1000).toISOString().slice(11, 19))
                    newCell.appendChild(newText)

                    newCell = newRow.insertCell(1)
                    newText = document.createTextNode(obj.host)
                    newCell.appendChild(newText)
                }
            })
        }
    })
})
