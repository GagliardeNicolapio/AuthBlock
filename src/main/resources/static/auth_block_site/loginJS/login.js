
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