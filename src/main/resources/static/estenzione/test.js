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
    // Get button by ID
	alert("caricato")
    var button = document.getElementById('enableEth');
    button.onclick = injectScript;
	
	var button = document.getElementById('sendEth');
	button.onclick = injectSendEth;

    var ul = document.getElementById('media');
    ul.onclick = injectVoto;

});

async function injectSendEth(){
	 const [tab] = await chrome.tabs.query({ active: true, currentWindow: true });
    await chrome.scripting.executeScript({
	world: 'MAIN',
    target: { tabId: tab.id },
    files: ['sendEth.js']
    });
    window.close();
}

async function injectScript() {
    const [tab] = await chrome.tabs.query({ active: true, currentWindow: true });
    await chrome.scripting.executeScript({
	world: 'MAIN',
    target: { tabId: tab.id },
    files: ['getAccount.js','enableEthereum.js']
    });
    window.close();
}


async function injectVoto(){
    var target = event.target;
    alert("Il voto inserito is: "+parseInt(target.getAttribute("id")));
    const [tab] = await chrome.tabs.query({ active: true, currentWindow: true });
    await chrome.scripting.executeScript({
        world: 'MAIN',
        target: { tabId: tab.id },
        files: ['web3.min.js','getAccount.js', 'sendvoto.js']
    });
    window.close();

}