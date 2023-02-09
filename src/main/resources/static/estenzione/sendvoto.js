
/*
const web3 = new Web3(window.ethereum);
await window.ethereum.enable();*/
var AuthBlockLite = new web3.eth.Contract('0xde0B295669a9FD93d5F28D9Ec85E40f4cb697BAe', {
    from: '0x1234567890123456789012345678901234567891', // default from address
    gasPrice: '20000000000' // default gas price in wei, 20 gwei in this case
});

AuthBlockLite.methods.insertVoto(parseInt(target.getAttribute("id"))).send();