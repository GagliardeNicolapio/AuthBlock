
async function getAccount(){
    let account;
    if(window.web3){
        const selectedAccount = await window.ethereum
            . request({
                method:"eth_requestAccounts",
            })
            .then((accounts) => account = accounts[0])
            .catch(() => {
                alert("Nessun account selezionato")
                throw Error("Nessun account selezionato");
            });
    }else{alert("Devi installare MetaMask");}
    console.log(account)
    return account;
}