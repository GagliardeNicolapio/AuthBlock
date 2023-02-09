async function enableAccount(){
    if(typeof accounts !== 'undefined'){

    }else{
        const account = await ethereum.request({ method: 'eth_requestAccounts' });
        accounts[0] = account;
    }
}
enableAccount();
//const accounts =  window.ethereum.request({method: 'eth_requestAccounts'});
//provare a iniettare il tag script 
