//alert('qui');
/*function loadFile() {
  const chartContainer = document.createElement("canvas");
  chartContainer.setAttribute("id", "chartContainer");
  document.body.appendChild(chartContainer);

  const script = document.createElement("script");
  script.src = "https://cdn.jsdelivr.net/npm/chart.js";
  document.body.appendChild(script);
  script.onload = () => {
    const domScript = document.createElement("script");
    domScript.src = chrome.extension.getURL("js/dom/chart.js");
    document.body.appendChild(domScript);
  };
}*/
//loadFile();
/*
function loadFile2(){
	
var x = document.createElement("script");
x.innerText = "if(window.web3){alert('ok')}else{alert('nononono')}";
document.getElementsByTagName("body")[0].appendChild(x);

}
loadFile2();





var ul = document.getElementById('media');
ul.onclick = function(event) {
    var target = event.target;
    alert("Il voto inserito is: "+parseInt(target.getAttribute("id")));
    //injectVoto();

};*/


document.addEventListener('DOMContentLoaded', function () {

    injectContract()
    //document.getElementsByTagName("body")[0].onload = injectContract;

    // Get button by ID
    document.getElementById('cron').onclick = injectCronologia;


	document.getElementById('val').onclick = injectMedia;

    document.getElementById('enableEth').onclick = injectScript;

	
	document.getElementById('sendEth').onclick = injectSendEth;


    document.getElementById('media').onclick = injectVoto;
});

async function injectContract(){
    const [tab] = await chrome.tabs.query({ active: true, currentWindow: true });
    await chrome.scripting.executeScript({
        world: 'MAIN',
        target: { tabId: tab.id },
        files: ['web3.min.js','getAccount.js','contract.js']
    });
   // window.close();
}

async function injectCronologia(){
    var newURL = "localhost:8080/dashboardLite";
    chrome.tabs.create({ url: newURL });
}
async function injectMedia(){
    const [tab] = await chrome.tabs.query({ active: true, currentWindow: true });
    await chrome.scripting.executeScript({
        world: 'MAIN',
        target: { tabId: tab.id },
        files: ['getMedia.js']
    });
    window.close();
}
async function injectSendEth(){
	 const [tab] = await chrome.tabs.query({ active: true, currentWindow: true });
    await chrome.scripting.executeScript({
	world: 'MAIN',
    target: { tabId: tab.id },
    files: ['web3.min.js','getAccount.js','sendAccess.js']
    });
    window.close();
}

async function injectScript() {
    const [tab] = await chrome.tabs.query({ active: true, currentWindow: true });
    await chrome.scripting.executeScript({
	world: 'MAIN',
    target: { tabId: tab.id },
    files: ['web3.min.js','getAccount.js','enableEthereum.js']
    });
    window.close();
}

/*
async function injectVoto(){
    var target = event.target;
    const [tab] = await chrome.tabs.query({ active: true, currentWindow: true });
    let voto = parseInt(target.getAttribute("id"))
    alert(voto);

    await chrome.scripting.executeScript({

        world: 'MAIN',
        target: { tabId: tab.id },
        files: ['sendvoto.js']
    });
    window.close();

}*/

function scrivi(resultsArray){
    console.log("resultsARRR: "+resultsArray)
}


//test
async function injectVoto(){
    var target = event.target;
    const [tab] = await chrome.tabs.query({ active: true, currentWindow: true });
    let voto = parseInt(target.getAttribute("id"))
    alert(voto);

    await chrome.scripting.executeScript({

        world: 'MAIN',
        target: { tabId: tab.id },
        args: [voto],
        func: prova
    });
    window.close();

}





function prova(numero) {
    function voto(numero,account) {
        alert("numero da voto: "+numero)

        var myContract = new web3.eth.Contract(abi, addressContract, {
            from: account,
            gasPrice: '2000000',
            gas: 1000000,
            data: bin
        })

        console.log(addressContract)
        console.log(account)

        console.log(myContract)

        //da decommenmy
        myContract.methods.insertVoto(numero).send({from: account}).then((receipt) => {
            console.log('receipt insertvoto: ' + receipt)
        })

        //test
        //let accesso = ['google.com','23:30']
        //myContract.methods.insertAccesso(account,accesso).send({from:account, value:700000000000000}).then(function(receipt){console.log('pippo'+receipt)})
    }
    console.log("prova")
    if (typeof account === 'undefined') {
        console.log("prova if")

        var account;
        getAccount(voto,numero)

    } else {
        console.log("prova else")

        voto(numero)
    }

    console.log("fine")
    return "pippo";

}

