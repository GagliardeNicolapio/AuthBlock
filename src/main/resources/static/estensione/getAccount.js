var account;
async function getAccount(fun,numero=0){

    if(window.web3){

        const selectedAccount = await window.ethereum
            . request({
                method:"eth_requestAccounts",
            })
            .then((accounts) => {
                account = accounts[0]
                console.log("aaaacc:"+account)
                if(numero==0){
                    console.log("chiamo fun con "+numero)
                    fun()
                }else{
                    console.log("else chiamo fun con "+numero)

                    fun(numero,account)
                }
            })
            .catch(() => {
                alert("Nessun account selezionato")
                throw Error("Nessun account selezionato");
            });

    }else{alert("Devi installare MetaMask");}
}


console.log("getAccount caricato")