
/*
const web3 = new Web3(window.ethereum);
await window.ethereum.enable();*/
/*var AuthBlockLite = new web3.eth.Contract('0xde0B295669a9FD93d5F28D9Ec85E40f4cb697BAe', {
    from: '0x1234567890123456789012345678901234567891', // default from address
    gasPrice: '20000000000' // default gas price in wei, 20 gwei in this case
});


//AuthBlockLite.methods.insertVoto(parseInt(target.getAttribute("id"))).send();
async function test(){
    let transactionParam = {to: 0x25621E55Ed1Ff8cA0432DdCf006E0b558F1E21E5, from: 0x8c0EC3883E95c6a678700398ceca796C386f4E90, value:0x38D7EA4C68000};

    const accounts = await ethereum.request({method:'insertVoto', params:[transactionParam]}).then(txhash =>{console.log(txhash)});
}

test()*/


async function loadWeb3() {
    if (window.ethereum) {
        window.web3 = new Web3(window.ethereum);
        window.ethereum.enable();
    }
}

async function loadContract() {
    console.log("load gay")
    let abi = [{"inputs":[{"internalType":"address","name":"indirizzo","type":"address"}],"name":"getInfoAccesso","outputs":[{"internalType":"string","name":"","type":"string"}],"stateMutability":"view","type":"function"},{"inputs":[],"name":"getMedia","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"address","name":"indirizzo","type":"address"},{"internalType":"string[]","name":"_dataInfoAccesso","type":"string[]"}],"name":"insertAccesso","outputs":[],"stateMutability":"payable","type":"function"},{"inputs":[{"internalType":"address","name":"indirizzo","type":"address"},{"internalType":"uint8","name":"vot","type":"uint8"}],"name":"insertVoto","outputs":[],"stateMutability":"nonpayable","type":"function"}]// your abi here
    let address = "0xCD1d977DcA6c43318624f114be1179fD413A69E5"  // your contract address here
    return await new window.web3.eth.Contract(abi, address);
}
async function getCurrentAccount() {
    console.log("account gay")
    if(typeof accounts !== 'undefined'){
        console.log("account if gay")
        return accounts[0];
    }else{
        console.log("account else gay")
        const accounts = await window.ethereum.request({ method: 'eth_requestAccounts' });
        console.log("cazzononiine"+accounts);
        return accounts[0];
    }

}

async function run() {
    console.log("run gay")
    await loadWeb3();
    console.log("await gay")
    window.contract = await loadContract();
    const account = await getCurrentAccount();
    console.log("dopo account gay"+parseInt(target.getAttribute("id")))
    console.log(account)
    let result = await window.contract.methods.insertVoto("0x04d1dee604aFaa2753f033e8a9372764a55a3e96",parseInt(target.getAttribute("id"))).send({from: account});
    console.log(result)
}

if (typeof window.ethereum !== 'undefined') {
    console.log("if gay")
    run();
}







