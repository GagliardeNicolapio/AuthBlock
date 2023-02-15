var account;
async function getAccount(){

    if(window.web3){

        const selectedAccount = await window.ethereum
            . request({
                method:"eth_requestAccounts",
            })
            .then((accounts) => {
                account = accounts[0]
                console.log("aaaacc:"+account)})
            .catch(() => {
                alert("Nessun account selezionato")
                throw Error("Nessun account selezionato");
            });

    }else{alert("Devi installare MetaMask");}
}
getAccount();

console.log("getAccount caricato")