
/*
const web3 = new Web3(window.ethereum);
await window.ethereum.enable();*/
/*var AuthBlockLite = new web3.eth.Contract('0xde0B295669a9FD93d5F28D9Ec85E40f4cb697BAe', {
    from: '0x1234567890123456789012345678901234567891', // default from address
    gasPrice: '20000000000' // default gas price in wei, 20 gwei in this case
});*/


//AuthBlockLite.methods.insertVoto(parseInt(target.getAttribute("id"))).send();
async function test(){
    let transactionParam = {to: 0x25621E55Ed1Ff8cA0432DdCf006E0b558F1E21E5, from: 0x8c0EC3883E95c6a678700398ceca796C386f4E90, value:0x38D7EA4C68000};

    const accounts = await ethereum.request({method:'insertVoto', params:[transactionParam]}).then(txhash =>{console.log(txhash)});
}

test()