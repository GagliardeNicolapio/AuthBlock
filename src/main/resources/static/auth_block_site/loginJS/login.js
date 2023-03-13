
function truncateAddress(address) {
    if (!address) {
        return "";
    }
    return `${address.substr(0, 5)}...${address.substr(
        address.length - 5,
        address.length
    )}`;
}


// Display or remove the users know address on the frontend
function showAddress() {
    if (!window.userAddress) {
        document.getElementById("userAddress").innerText = "";
        document.getElementById("logoutButton").classList.add("hidden");
        return false;
    }

    window.alert(`ETH Address: ${truncateAddress(window.userAddress)}`);
    //document.getElementById(
     //   "userAddress"
    //).innerText = `ETH Address: ${truncateAddress(window.userAddress)}`;
    //document.getElementById("logoutButton").classList.remove("hidden");
}

// remove stored user address and reset frontend
function logout() {
    window.userAddress = null;
    window.localStorage.removeItem("userAddress");
    showAddress();
}
function control(){
    let address = document.getElementById("addressEther")
    let result = window.Web3.utils.isAddress(address)
    window.alert(result) // => true
}
// Login with Web3 via Metamasks window.ethereum library
async function loginWithEth() {
    if (window.web3) {
        try {
            // We use this since ethereum.enable() is deprecated. This method is not
            // available in Web3JS - so we call it directly from metamasks' library
            const selectedAccount = await window.ethereum
                .request({
                    method: "eth_requestAccounts",
                })
                .then((accounts) => accounts[0])
                .catch(() => {
                    throw Error("No account selected!");
                });
            window.userAddress = selectedAccount;
            window.localStorage.setItem("userAddress", selectedAccount);
            document.getElementById("addressEther").value = selectedAccount
        } catch (error) {
            console.error(error);
        }
    } else {
        alert("No ETH brower extension detected.");
    }
}

async function loginWithEthIndex(){
    if(window.web3){
        const selectedAccount = await window.ethereum
            . request({
                method:"eth_requestAccounts",
            })
            .then((accounts) => accounts[0])
            .catch(() => {
                alert("Nessun account selezionato")
                throw Error("Nessun account selezionato");
            });
        document.getElementById("loginFormInput").value = selectedAccount;
        document.getElementById("loginForm").submit();
    }else{alert("Devi installare MetaMask");}
}

async function pagamentoFull(){
    let abi =[{"anonymous":false,"inputs":[{"indexed":false,"internalType":"address","name":"indirizzoSito","type":"address"},{"indexed":false,"internalType":"address","name":"indirizzoUtente","type":"address"},{"indexed":false,"internalType":"uint256","name":"idAccessoSito","type":"uint256"},{"indexed":false,"internalType":"uint256","name":"idAccessoUtente","type":"uint256"}],"name":"InserimentoAccessoFatto","type":"event"},{"anonymous":false,"inputs":[{"indexed":true,"internalType":"address","name":"previousOwner","type":"address"},{"indexed":true,"internalType":"address","name":"newOwner","type":"address"}],"name":"OwnershipTransferred","type":"event"},{"anonymous":false,"inputs":[{"indexed":false,"internalType":"address","name":"indirizzoSito","type":"address"}],"name":"PagamentoRicevuto","type":"event"},{"inputs":[{"internalType":"address","name":"_indirizzoSito","type":"address"},{"internalType":"address","name":"_indirizzoUtente","type":"address"}],"name":"checkUser","outputs":[{"internalType":"bool","name":"","type":"bool"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"address","name":"indirizzoSito","type":"address"},{"internalType":"uint256","name":"accessNumber","type":"uint256"}],"name":"getInfoAccessoSito","outputs":[{"internalType":"string","name":"","type":"string"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"address","name":"indirizzoSito","type":"address"},{"internalType":"uint256","name":"start","type":"uint256"},{"internalType":"uint256","name":"end","type":"uint256"}],"name":"getInfoAccessoSitoRange","outputs":[{"internalType":"string","name":"","type":"string"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"address","name":"indirizzoUtente","type":"address"},{"internalType":"uint256","name":"start","type":"uint256"},{"internalType":"uint256","name":"end","type":"uint256"}],"name":"getInfoAccessoUtentiRange","outputs":[{"internalType":"string","name":"","type":"string"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"address","name":"indirizzoSito","type":"address"}],"name":"getNumberAccessiSito","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"address","name":"indirizzoUtente","type":"address"}],"name":"getNumberAccessiUtente","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"address","name":"_indirizzoSito","type":"address"},{"internalType":"address","name":"_indirizzoUtente","type":"address"},{"internalType":"string[]","name":"_dataInfoAccessoSito","type":"string[]"},{"internalType":"string[]","name":"_dataInfoAccessoUtente","type":"string[]"}],"name":"insertAccesso","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"address","name":"_indirizzoSito","type":"address"},{"internalType":"address","name":"_indirizzoUtente","type":"address"},{"internalType":"uint256","name":"idInfoSito","type":"uint256"},{"internalType":"uint256","name":"idInfoUtente","type":"uint256"}],"name":"insertLogout","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"address","name":"_indirizzoSito","type":"address"},{"internalType":"address","name":"_indirizzoUtente","type":"address"},{"internalType":"string[]","name":"_dataInfoAccessoSito","type":"string[]"},{"internalType":"string[]","name":"_dataInfoAccessoUtente","type":"string[]"}],"name":"insertUser","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"address","name":"indirizzo","type":"address"}],"name":"isPresent","outputs":[{"internalType":"bool","name":"","type":"bool"}],"stateMutability":"view","type":"function"},{"inputs":[],"name":"owner","outputs":[{"internalType":"address","name":"","type":"address"}],"stateMutability":"view","type":"function"},{"inputs":[],"name":"pagamentoFull","outputs":[],"stateMutability":"payable","type":"function"},{"inputs":[],"name":"renounceOwnership","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"address","name":"newOwner","type":"address"}],"name":"transferOwnership","outputs":[],"stateMutability":"nonpayable","type":"function"}]
    let addressContract = "0x19053a02Aec925fB51E3bf06ab2eF75952E0CDbD";
    let bin = "608060405234801561001057600080fd5b5061001a3361001f565b61006f565b600080546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b611b828061007e6000396000f3fe6080604052600436106100dd5760003560e01c8063761bdc1e1161007f578063b023ff5b11610059578063b023ff5b14610247578063bf0e054a1461028b578063d587eaa3146102c1578063f2fde38b146102c957600080fd5b8063761bdc1e146101df5780638da5cb5b146101ff5780639a133fae1461022757600080fd5b806350f18633116100bb57806350f186331461016a578063557c3d5a1461018a578063715018a6146101aa578063715fbd8f146101bf57600080fd5b806332ea715f146100e25780633575a1ff14610118578063373cf2021461013a575b600080fd5b3480156100ee57600080fd5b506101026100fd366004611465565b6102e9565b60405161010f91906114bc565b60405180910390f35b34801561012457600080fd5b506101386101333660046114ef565b61068c565b005b34801561014657600080fd5b5061015a610155366004611531565b610802565b604051901515815260200161010f565b34801561017657600080fd5b506101386101853660046116a0565b610843565b34801561019657600080fd5b506101026101a5366004611465565b610b19565b3480156101b657600080fd5b50610138610d68565b3480156101cb57600080fd5b506101386101da3660046116a0565b610d7c565b3480156101eb57600080fd5b5061015a6101fa366004611725565b610e47565b34801561020b57600080fd5b506000546040516001600160a01b03909116815260200161010f565b34801561023357600080fd5b50610102610242366004611747565b610e86565b34801561025357600080fd5b5061027d610262366004611725565b6001600160a01b031660009081526003602052604090205490565b60405190815260200161010f565b34801561029757600080fd5b5061027d6102a6366004611725565b6001600160a01b031660009081526001602052604090205490565b6101386110b0565b3480156102d557600080fd5b506101386102e4366004611725565b611135565b60606102f36111ae565b8183106103475760405162461bcd60e51b815260206004820152601a60248201527f7374617274206e6f6e206527206d696e6f726520646920656e6400000000000060448201526064015b60405180910390fd5b6001600160a01b0384166000908152600160205260409020548211156103ab5760405162461bcd60e51b8152602060048201526019602482015278195b99081949c81b5859d9da5bdc9948191a481b195b99da1d603a1b604482015260640161033e565b6001600160a01b038416600090815260016020526040812080546105c29190869081106103da576103da611771565b90600052602060002090600502016040518060a0016040529081600082015481526020016001820154815260200160028201805461041790611787565b80601f016020809104026020016040519081016040528092919081815260200182805461044390611787565b80156104905780601f1061046557610100808354040283529160200191610490565b820191906000526020600020905b81548152906001019060200180831161047357829003601f168201915b505050505081526020016003820180546104a990611787565b80601f01602080910402602001604051908101604052809291908181526020018280546104d590611787565b80156105225780601f106104f757610100808354040283529160200191610522565b820191906000526020600020905b81548152906001019060200180831161050557829003601f168201915b5050505050815260200160048201805461053b90611787565b80601f016020809104026020016040519081016040528092919081815260200182805461056790611787565b80156105b45780601f10610589576101008083540402835291602001916105b4565b820191906000526020600020905b81548152906001019060200180831161059757829003601f168201915b505050505081525050611208565b6040516020016105d291906117c1565b60408051601f19818403018152919052905060006105f1856001611800565b90505b83811015610661576001600160a01b03861660009081526001602052604090208054839161062c91849081106103da576103da611771565b60405160200161063d929190611813565b604051602081830303815290604052915080806106599061184f565b9150506105f4565b50806040516020016106739190611868565b60408051808303601f1901815291905295945050505050565b6106946111ae565b6001600160a01b03841660009081526001602052604090205482106106b857600080fd5b6001600160a01b03831660009081526003602052604090205481106106dc57600080fd5b6001600160a01b038316600090815260036020526040902080548290811061070657610706611771565b90600052602060002090600302016002015460001461072457600080fd5b6001600160a01b038416600090815260016020526040902080548390811061074e5761074e611771565b90600052602060002090600502016001015460001461076c57600080fd5b6001600160a01b03841660009081526001602052604090208054429182918590811061079a5761079a611771565b9060005260206000209060050201600101819055508060036000866001600160a01b03166001600160a01b0316815260200190815260200160002083815481106107e6576107e6611771565b9060005260206000209060030201600201819055505050505050565b6001600160a01b0380831660009081526002602090815260408083209385168352929052908120805482919061083790611787565b90501190505b92915050565b61084b6111ae565b6040805160a08101825242808252600060208084018290526001600160a01b03808a1683526002825285832090891683529052838120805492949193929183019161089590611787565b80601f01602080910402602001604051908101604052809291908181526020018280546108c190611787565b801561090e5780601f106108e35761010080835404028352916020019161090e565b820191906000526020600020905b8154815290600101906020018083116108f157829003601f168201915b505050505081526020018560018151811061092b5761092b611771565b602002602001015181526020018560028151811061094b5761094b611771565b60200260200101518152509050600060405180606001604052808560008151811061097857610978611771565b6020908102919091018101518252818101869052600060409283018190526001600160a01b038b168152600180835283822080548083018255908352918390208751600590930201918255918601519181019190915590840151919250839160028201906109e690826118dc565b50606082015160038201906109fb90826118dc565b5060808201516004820190610a1090826118dc565b5050506001600160a01b03861660009081526003602081815260408320805460018101825590845292208351849390920201908190610a4f90826118dc565b50602082015181600101556040820151816002015550507ff158013eb12ed8b7af55bab269a0776b618bb20341f45c634b93991a09554048878760018060008c6001600160a01b03166001600160a01b0316815260200190815260200160002080549050610abd919061199c565b6001600160a01b038a16600090815260036020526040902054610ae29060019061199c565b604080516001600160a01b03958616815294909316602085015291830152606082015260800160405180910390a150505050505050565b6060610b236111ae565b818310610b725760405162461bcd60e51b815260206004820152601a60248201527f7374617274206e6f6e206527206d696e6f726520646920656e64000000000000604482015260640161033e565b6001600160a01b038416600090815260036020526040902054821115610bd65760405162461bcd60e51b8152602060048201526019602482015278195b99081949c81b5859d9da5bdc9948191a481b195b99da1d603a1b604482015260640161033e565b6001600160a01b03841660009081526003602052604081208054610cc9919086908110610c0557610c05611771565b9060005260206000209060030201604051806060016040529081600082018054610c2e90611787565b80601f0160208091040260200160405190810160405280929190818152602001828054610c5a90611787565b8015610ca75780601f10610c7c57610100808354040283529160200191610ca7565b820191906000526020600020905b815481529060010190602001808311610c8a57829003601f168201915b505050505081526020016001820154815260200160028201548152505061125b565b604051602001610cd991906117c1565b60408051601f1981840301815291905290506000610cf8856001611800565b90505b83811015610661576001600160a01b038616600090815260036020526040902080548391610d339184908110610c0557610c05611771565b604051602001610d44929190611813565b60405160208183030381529060405291508080610d609061184f565b915050610cfb565b610d706111ae565b610d7a600061128e565b565b610d846111ae565b600082600281518110610d9957610d99611771565b60200260200101515111610de55760405162461bcd60e51b8152602060048201526013602482015272757365726e616d65206e6f6e2076616c69646160681b604482015260640161033e565b81600281518110610df857610df8611771565b6020908102919091018101516001600160a01b03808716600090815260028452604080822092881682529190935290912090610e3490826118dc565b50610e4184848484610843565b50505050565b6001600160a01b03811660009081526001602052604081205415158061083d5750506001600160a01b0316600090815260036020526040902054151590565b6060610e906111ae565b6001600160a01b0383166000908152600160205260408120805484908110610eba57610eba611771565b90600052602060002090600502016040518060a00160405290816000820154815260200160018201548152602001600282018054610ef790611787565b80601f0160208091040260200160405190810160405280929190818152602001828054610f2390611787565b8015610f705780601f10610f4557610100808354040283529160200191610f70565b820191906000526020600020905b815481529060010190602001808311610f5357829003601f168201915b50505050508152602001600382018054610f8990611787565b80601f0160208091040260200160405190810160405280929190818152602001828054610fb590611787565b80156110025780601f10610fd757610100808354040283529160200191611002565b820191906000526020600020905b815481529060010190602001808311610fe557829003601f168201915b5050505050815260200160048201805461101b90611787565b80601f016020809104026020016040519081016040528092919081815260200182805461104790611787565b80156110945780601f1061106957610100808354040283529160200191611094565b820191906000526020600020905b81548152906001019060200180831161107757829003601f168201915b50505050508152505090506110a881611208565b949350505050565b670de0b6b3a764000034116111005760405162461bcd60e51b81526020600482015260166024820152752134b9b7b3b730903830b3b0b93290189032ba3432b960511b604482015260640161033e565b6040513381527fb82feae9da1971f4db0fe644ffdcd97a911db5ce2ca18420226c08831a30e3f09060200160405180910390a1565b61113d6111ae565b6001600160a01b0381166111a25760405162461bcd60e51b815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201526564647265737360d01b606482015260840161033e565b6111ab8161128e565b50565b6000546001600160a01b03163314610d7a5760405162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604482015260640161033e565b606061121782600001516112de565b61122483602001516112de565b604080850151606086015160808701519251611245959493906020016119af565b6040516020818303038152906040529050919050565b6060816000015161126f83602001516112de565b61127c84604001516112de565b60405160200161124593929190611ab0565b600080546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b606060006112eb83611371565b600101905060008167ffffffffffffffff81111561130b5761130b611564565b6040519080825280601f01601f191660200182016040528015611335576020820181803683370190505b5090508181016020015b600019016f181899199a1a9b1b9c1cb0b131b232b360811b600a86061a8153600a850494508461133f57509392505050565b60008072184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b83106113b05772184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b830492506040015b6d04ee2d6d415b85acef810000000083106113dc576d04ee2d6d415b85acef8100000000830492506020015b662386f26fc1000083106113fa57662386f26fc10000830492506010015b6305f5e1008310611412576305f5e100830492506008015b612710831061142657612710830492506004015b60648310611438576064830492506002015b600a831061083d5760010192915050565b80356001600160a01b038116811461146057600080fd5b919050565b60008060006060848603121561147a57600080fd5b61148384611449565b95602085013595506040909401359392505050565b60005b838110156114b357818101518382015260200161149b565b50506000910152565b60208152600082518060208401526114db816040850160208701611498565b601f01601f19169190910160400192915050565b6000806000806080858703121561150557600080fd5b61150e85611449565b935061151c60208601611449565b93969395505050506040820135916060013590565b6000806040838503121561154457600080fd5b61154d83611449565b915061155b60208401611449565b90509250929050565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff811182821017156115a3576115a3611564565b604052919050565b6000601f83818401126115bd57600080fd5b8235602067ffffffffffffffff808311156115da576115da611564565b8260051b6115e983820161157a565b938452868101830193838101908986111561160357600080fd5b84890192505b85831015611693578235848111156116215760008081fd5b8901603f81018b136116335760008081fd5b8581013560408682111561164957611649611564565b61165a828b01601f1916890161157a565b8281528d8284860101111561166f5760008081fd5b828285018a8301376000928101890192909252508352509184019190840190611609565b9998505050505050505050565b600080600080608085870312156116b657600080fd5b6116bf85611449565b93506116cd60208601611449565b9250604085013567ffffffffffffffff808211156116ea57600080fd5b6116f6888389016115ab565b9350606087013591508082111561170c57600080fd5b50611719878288016115ab565b91505092959194509250565b60006020828403121561173757600080fd5b61174082611449565b9392505050565b6000806040838503121561175a57600080fd5b61176383611449565b946020939093013593505050565b634e487b7160e01b600052603260045260246000fd5b600181811c9082168061179b57607f821691505b6020821081036117bb57634e487b7160e01b600052602260045260246000fd5b50919050565b605b60f81b8152600082516117dd816001850160208701611498565b9190910160010192915050565b634e487b7160e01b600052601160045260246000fd5b8082018082111561083d5761083d6117ea565b60008351611825818460208801611498565b600b60fa1b9083019081528351611843816001840160208801611498565b01600101949350505050565b600060018201611861576118616117ea565b5060010190565b6000825161187a818460208701611498565b605d60f81b920191825250600101919050565b601f8211156118d757600081815260208120601f850160051c810160208610156118b45750805b601f850160051c820191505b818110156118d3578281556001016118c0565b5050505b505050565b815167ffffffffffffffff8111156118f6576118f6611564565b61190a816119048454611787565b8461188d565b602080601f83116001811461193f57600084156119275750858301515b600019600386901b1c1916600185901b1785556118d3565b600085815260208120601f198616915b8281101561196e5788860151825594840194600190910190840161194f565b508582101561198c5787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b8181038181111561083d5761083d6117ea565b6a3db7b930a637b3b4b71d1160a91b815285516000906119d681600b850160208b01611498565b6d11161037b930a637b3b7baba1d1160911b600b918401918201528651611a04816019840160208b01611498565b6c1116103ab9b2b93730b6b29d1160991b601992909101918201528551611a32816026840160208a01611498565b6d1116103ab9b2b920b3b2b73a1d1160911b602692909101918201528451611a61816034840160208901611498565b6d11161034b820b2323932b9b99d1160911b603492909101918201528351611a90816042840160208801611498565b01611aa26042820161227d60f01b9052565b604401979650505050505050565b693dbab93629b4ba379d1160b11b81528351600090611ad681600a850160208901611498565b6b111637b930a637b3b4b71d1160a11b600a918401918201528451611b02816016840160208901611498565b6c111637b930a637b3b7baba1d1160991b601692909101918201528351611b30816023840160208801611498565b61227d60f01b602392909101918201526025019594505050505056fea26469706673582212208899850ff0663ab9fff77bf2c1413484a6151e931df9946909a15a1bde0e324864736f6c63430008120033"
    let web3 = new Web3(window.ethereum);
    web3.setProvider(window.ethereum)

    const selectedAccount = await window.ethereum
        . request({
            method:"eth_requestAccounts",
        })
        .then((accounts) => {
            accounts[0]
            let myContract = new web3.eth.Contract(abi, addressContract, {from: account, gasPrice:'2000000', gas:1000000, data:bin})
            myContract.methods.insertVoto(2).send({from:account}).then((receipt)=>{console.log('receipt insertvoto: '+receipt)})
        })
        .catch(() => {
            alert("Nessun account selezionato")
            throw Error("Nessun account selezionato");
        });



}